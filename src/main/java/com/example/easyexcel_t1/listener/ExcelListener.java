package com.example.easyexcel_t1.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.easyexcel_t1.entity.Student;
import com.example.easyexcel_t1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author by pepsi-wyl
 * @date 2022-11-15 20:16
 */

// 读监听器
@Component
@Scope("prototype")
public class ExcelListener extends AnalysisEventListener<Student> {

    // 存储读取的Excel信息
    private ArrayList<Student> students = new ArrayList<>();

    // 调用服务插入数据库
    private final StudentService studentService;

    public ExcelListener(StudentService studentService) {
        this.studentService = studentService;
    }

    // 读取excel表头信息，headMap即为表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    // 读取完一行调用一次，通常在此次存入数据库
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        // 存入容器
        students.add(student);
        // 插入数据库
        if (students.size() % 5 == 0) {
            studentService.readExcel(students);
            students.clear();
        }
    }

    // 文档读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("StudentExcel读取完毕");
    }
}
