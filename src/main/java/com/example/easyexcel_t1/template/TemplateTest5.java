package com.example.easyexcel_t1.template;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.example.easyexcel_t1.entity.FileData;
import com.example.easyexcel_t1.entity.Members;
import com.example.easyexcel_t1.entity.Student;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-11-16 10:59
 */

// 报表导出
public class TemplateTest5 {
    public static void main(String[] args) {

        // 准备模板
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/report_template.xlsx");
        // 准备输入文件
        String targetFileName = "down/Excel_填充_报表导出.xlsx";

        // ****** 准备数据 *******
        HashMap<String, String> data = new HashMap<String, String>();
        // 日期
        data.put("date", "2020-03-16");
        // 总会员数
        data.put("totalCount", "1000");
        // 新增员数
        data.put("increaseCount", "100");
        // 本周新增会员数
        data.put("increaseCountWeek", "50");
        // 本月新增会员数
        data.put("increaseCountMonth", "100");
        // 新增会员数据
        List<Members> members = initData();
        // **** 准备数据结束****

        // 创建工作簿对象 关联模板和输入文件
        ExcelWriter workBook = EasyExcel.write(targetFileName, FileData.class).withTemplate(template).build();
        // 创建工资表对象
        WriteSheet sheet = EasyExcel.writerSheet().build();
        // 填充数据 关联数据
        workBook.fill(data, sheet);    // 单组数据
        workBook.fill(members, sheet); // 多组数据
        // 关闭流！！！
        workBook.finish();
    }

    private static List<Members> initData() {
        ArrayList<Members> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Date date = Date.from(LocalDate.of(2001, i + 1, 17).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Members members = Members.builder().name("Ylan" + i).gender("男").birthday(date).build();
            list.add(members);
        }
        return list;
    }
}
