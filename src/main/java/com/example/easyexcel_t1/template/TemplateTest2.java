package com.example.easyexcel_t1.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.easyexcel_t1.entity.FileData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-11-16 9:28
 */

// 多组填充
public class TemplateTest2 {
    public static void main(String[] args) {
        // 准备模板
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/fill_data_template2.xlsx");
        // 准备输入文件
        String targetFileName = "down/Excel_填充_多组数据.xlsx";
        // 准备数据 实体类集合
        List<FileData> dataList = initFillData();

        // 创建工作簿对象 关联模板和输入文件
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(targetFileName, FileData.class).withTemplate(template);
        // 创建工资表对象
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
        // 填充数据 关联数据
        sheet.doFill(dataList);
    }

    private static List<FileData> initFillData() {
        ArrayList<FileData> fillDatas = new ArrayList<FileData>();
        for (int i = 0; i < 10; i++) fillDatas.add(FileData.builder().name("YLan" + i).age(10 + i).build());
        return fillDatas;
    }
}