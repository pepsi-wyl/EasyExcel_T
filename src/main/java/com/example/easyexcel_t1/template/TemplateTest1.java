package com.example.easyexcel_t1.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.easyexcel_t1.entity.FileData;

import java.io.InputStream;

/**
 * @author by pepsi-wyl
 * @date 2022-11-15 21:33
 */

// 单组填充
public class TemplateTest1 {
    public static void main(String[] args) {
        // 准备模板
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/fill_data_template1.xlsx");
        // 准备输入文件
        String targetFileName = "down/Excel_填充_单组数据.xlsx";
        // 准备数据 实体类
        FileData data = FileData.builder().name("Ylan").age(21).build();
        // 准备数据 Map类
        //        HashMap<String, String> data = new HashMap<>();
        //        data.put("name", "pepsi-wyl");
        //        data.put("age", "21");

        // 创建工作簿对象 关联模板和输入文件
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(targetFileName, FileData.class).withTemplate(template);
        // 创建工资表对象
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        // 填充数据 关联数据
        sheet.doFill(data);
    }
}