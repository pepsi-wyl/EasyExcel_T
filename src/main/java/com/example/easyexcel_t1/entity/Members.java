package com.example.easyexcel_t1.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2022-11-16 11:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

//  @Accessors(chain = true) 引发部分字段没法读取或者写入
@Builder
public class Members {

    // 写入时候忽略该字段
    @ExcelIgnore
    private String id;

    // index为Excel表的索引位置 0为第一列
    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String gender;

    @ExcelProperty(index = 2)
    private Date birthday;    // 只支持Date，不支持LocalDate和LocalDateTime
}
