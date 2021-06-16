package com.hhj.shop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class Shopcart{

    /**
     * 购物车id
     */
    private Integer id;

    /**
     * 用户id
     */
    @NotNull
    @JsonProperty(value = "mId")
    private Integer mId;

    /**
     * 商品id
     */
    @NotNull
    @JsonProperty(value = "gId")
    private Integer gId;

    @NotNull
    @JsonProperty(value = "status")
    private Integer status;

    /**
     * 商品数量
     */
    private Integer number;
}
