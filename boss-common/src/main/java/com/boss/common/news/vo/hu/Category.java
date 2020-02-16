package com.boss.common.news.vo.hu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -6436068674705620613L;

    private List<CategoryItem> items;

}
