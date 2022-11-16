package com.example.easyexcel_t1.service;

import com.alibaba.excel.EasyExcel;
import com.example.easyexcel_t1.entity.Student;
import com.example.easyexcel_t1.listener.StudentListener;

import java.io.InputStream;

/**
 * @author by pepsi-wyl
 * @date 2022-11-14 20:25
 */

// 读取Excel
public class Excel_Read {
    public static void main(String[] args) {
        // 1.文件的路径 2.实体类的Class 3.读监听器读
        // 获取Resource下的文件
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("student.xls");
        EasyExcel.read(
                        inputStream,
                        Student.class,
                        new StudentListener()
                )
                .sheet()   // 默认为表格1
                .doRead(); // 开始读取
    }
}