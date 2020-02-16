package com.boss.tools.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExportBean {
    List<String> list;
    List<List<String>> lists;
    List<List<String>> vin_repeat;
    List<List<String>> vin_duplicate;
}
