package com.spring.customAnnotation.entity;

import com.spring.customAnnotation.anno.Entity;
import lombok.Data;

@Data
@Entity("city")
public class CityEntity {
    private String id;
    private String name;
}
