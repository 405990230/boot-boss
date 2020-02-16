package com.boss.common.news.vo.hu;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageCompressionData implements Serializable {

    private static final long serialVersionUID = 3198353217459110608L;

    private String imgUrl;
    private String imageId;
    private Integer height;
    private Integer width;


}
