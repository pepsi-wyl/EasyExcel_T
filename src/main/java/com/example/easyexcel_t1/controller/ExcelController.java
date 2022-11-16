package com.example.easyexcel_t1.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.example.easyexcel_t1.entity.Student;
import com.example.easyexcel_t1.listener.ExcelListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author by pepsi-wyl
 * @date 2022-11-15 20:01
 */

// 处理web环境的Excel文件
@Controller("excelController")
@RequestMapping("/excel")
public class ExcelController {

    // 注入Excel读取监听器
    private final ExcelListener excelListener;

    public ExcelController(ExcelListener excelListener) {
        this.excelListener = excelListener;
    }

    // 处理上传的文件
    @ResponseBody
    @PostMapping("/read")
    public Map<String, String> readExcel(@RequestPart("file") MultipartFile file) {
        HashMap<String, String> map = new HashMap<>();
        try {
            // 读取Excel文件
            ExcelReaderBuilder read = EasyExcel.read(file.getInputStream(), Student.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            map.put("message", "success");
        } catch (IOException e) {
            map.put("message", "error");
            throw new RuntimeException(e);
        }
        return map;
    }

    @RequestMapping(value = "/write")
    public void write(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet().doWrite(data());
    }

    private List<Student> data() {
        // 准备数据
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Date date = Date.from(LocalDate.of(2001, i + 1, 17).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Student student = Student.builder().name("Ylan" + i).gender("男").birthDate(date).build();
            students.add(student);
        }
        return students;
    }

}
