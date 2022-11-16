package com.example.easyexcel_t1.service;

import com.example.easyexcel_t1.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-11-15 20:06
 */

@Service("studentService")
public class StudentService {
    // 调用数据库进行插入
    public void readExcel(List<Student> students) {
        students.forEach(student -> {
            System.out.println("插入" + student);
        });
    }
}
