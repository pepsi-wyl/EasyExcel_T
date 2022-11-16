package com.example.easyexcel_t1.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.easyexcel_t1.entity.Student;

import java.util.Map;

/**
 * @author by pepsi-wyl
 * @date 2022-11-14 20:38
 */

// Student读监听器
public class StudentListener extends AnalysisEventListener<Student> {

    // 读取excel表头信息，headMap即为表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    // 读取完一行调用一次
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("表体信息：" + student);
    }

    // 文档读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("StudentExcel读取完毕");
    }
}