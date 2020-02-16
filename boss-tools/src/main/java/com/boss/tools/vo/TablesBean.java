package com.boss.tools.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by qxr4383 on 2019/4/8.
 */
@Data
public class TablesBean {

    private String name;
    private List<ColumnsBean> columns;
    private List<List<String>> rows;

}
