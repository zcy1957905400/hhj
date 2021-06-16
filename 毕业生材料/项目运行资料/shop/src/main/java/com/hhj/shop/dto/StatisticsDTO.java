package com.hhj.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class StatisticsDTO {
    private String createdate;
    private BigDecimal value;
    private Integer total;

}
