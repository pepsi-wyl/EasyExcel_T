package com.example.easyexcel_t1.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.example.easyexcel_t1.entity.FileData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-11-16 9:58
 */

// 组合填充
public class TemplateTest3 {
    public static void main(String[] args) {
        // 准备模板
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/fill_data_template3.xlsx");
        // 准备输入文件
        String targetFileName = "down/Excel_填充_组合数据.xlsx";
        // 准备数据 实体类
        List<FileData> dataList = initFillData();
        // 准备数据 Map类
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("date", "2022-03-17");
        dataMap.put("total", "21");

        // 创建工作簿对象 关联模板和输入文件
        ExcelWriter workBook = EasyExcel.write(targetFileName, FileData.class).withTemplate(template).build();
        // 创建工资表对象
        WriteSheet sheet = EasyExcel.writerSheet().build();
        // 组合填充时，因为多组填充的数据量不确定，需要在多组填充完之后另起一行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();
        // 填充数据 关联数据
        workBook.fill(dataList, fillConfig, sheet); // 多组数据
        workBook.fill(dataMap, sheet);  // 单组数据
        // 关闭流！！！
        workBook.finish();
    }

    private static List<FileData> initFillData() {
        ArrayList<FileData> fillDatas = new ArrayList<FileData>();
        for (int i = 0; i < 10; i++) fillDatas.add(FileData.builder().name("YLan" + i).age(10 + i).build());
        return fillDatas;
    }
}