package com.hhj.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class Commission {
    /**
     * 佣金id
     */
    private Integer id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态
     */
    private Integer type;

    private User user;

   // private User user;
}
