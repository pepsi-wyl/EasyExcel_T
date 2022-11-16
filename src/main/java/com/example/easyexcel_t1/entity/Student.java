package com.example.easyexcel_t1.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.*;

import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2022-11-14 20:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

//  @Accessors(chain = true) 引发部分字段没法读取或者写入
@Builder

// 表头行高
@HeadRowHeight(25)
// 内容行高
@ContentRowHeight(15)
// 列宽 作用于全部
@ColumnWidth(20)

public class Student {

    // 写入时候忽略该字段
    @ExcelIgnore
    private String id;

    // Excel表头信息 value为表头的值 index为Excel表的索引位置 0为第一列
    @ExcelProperty(value = "学生姓名", index = 0)
    // 列宽
//    @ColumnWidth(20)
    private String name;

    @ExcelProperty(value = "学生性别", index = 1)
//    @ColumnWidth(20)
    private String gender;

    @ExcelProperty(value = "学生出生日期", index = 2)
//    @ColumnWidth(20)
    @DateTimeFormat("yyyy/MM/dd")  // 将Date格式转化为指定格式
    private Date birthDate;    // 只支持Date，不支持LocalDate和LocalDateTime

}
