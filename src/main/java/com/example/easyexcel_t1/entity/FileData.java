package com.example.easyexcel_t1.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author by pepsi-wyl
 * @date 2022-11-15 21:31
 */

@Data
@Builder
public class FileData {
    private String name;
    private int age;
}