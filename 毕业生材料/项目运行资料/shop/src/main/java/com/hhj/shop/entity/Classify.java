package com.hhj.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class Classify {
    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String clsName;

    /**
     * 简介
     */
    private String synopsis;

    /**
     * 状态
     */
    private Integer type;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 修改时间
     */
    private String updatetime;


}