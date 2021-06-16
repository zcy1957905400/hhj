package com.hhj.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;



@Data
@Getter
@Setter
public class GoodsDTO {

    private Integer id;

    private String goods_name;
    /**
     * 商品数量
     */
    private Integer number;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 展示图片
     */
    private String photos;

}
