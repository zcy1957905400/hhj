package com.hhj.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderG {
    /**
     * 订单商品关系id
     */
    private Integer id;

    /**
     * 订单id
     */
    private String oId;

    /**
     * 商品id
     */
    private Integer gId;

    /**
     * 商品数量
     */
    private Integer number;

}
