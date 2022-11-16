package com.example.easyexcel_t1.service;

import com.alibaba.excel.EasyExcel;
import com.example.easyexcel_t1.entity.Student;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2022-11-14 21:00
 */

// 写Excel
public class Excel_Write {
    public static void main(String[] args) {

        // 准备数据
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Date date = Date.from(LocalDate.of(2001, i + 1, 17).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Student student = Student.builder().name("Ylan" + i).gender("男").birthDate(date).build();
            students.add(student);
        }

        // 1.文件的路径 2.实体类的Class
        // 写入工程下down文件夹中
        EasyExcel.write(
                        "down/student_Write.xls",
                        Student.class
                ).sheet()   // 默认为表格1
                .doWrite(students);
    }
}
