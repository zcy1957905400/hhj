package com.hhj.shop.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class Withdrawal {
    /**
     * 提现记录id
     */
    private Integer id;

    /**
     * 自提点管理员id
     */
    @JsonProperty(value = "uId")
    private Integer uId;

    /**
     * 金额
     */
    @JsonProperty(value = "amount")
    private BigDecimal amount;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createtime;
}
