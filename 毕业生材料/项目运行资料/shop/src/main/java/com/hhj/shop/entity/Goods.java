package com.hhj.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public class Goods {

    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 展示图片
     */
    private String photos;

    /**
     * 详情
     */
    private String particulars;

    /**
     * 已销售数量
     */
    private Integer sales;

    /**
     * 是否轮播
     */
    private Integer carousel;

    /**
     * 分类id
     */
    private Integer clsId;

    /**
     * 规格
     */
    private String spec;

    /**
     * 状态
     */
    private Integer type;

    /**
     * 创建时间
     */
    private String createtime;

    private List<OrderG> orderGList;

    private Integer status;

    private Integer number;
}
