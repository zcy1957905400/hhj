package com.hhj.shop.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Region {
    /**
     * 地区id
     */
    private Integer id;

    /**
     * 省级行政区名
     */
    private String name;
}
