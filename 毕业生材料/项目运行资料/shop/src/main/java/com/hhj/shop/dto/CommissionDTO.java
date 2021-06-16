package com.hhj.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CommissionDTO {

    private String oid;
    private String uname;
    private BigDecimal amount;
    private String order_settlement_time;

}
