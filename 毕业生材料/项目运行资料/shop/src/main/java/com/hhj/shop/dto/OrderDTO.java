package com.hhj.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public class OrderDTO {

    /**
     * 用户id
     */
    @NotNull
    @JsonProperty(value = "memberId")
    private Integer memberId;

    @NotNull
    @JsonProperty(value = "ids")
    private Integer[] ids;

    @NotNull
    @JsonProperty(value = "goodAmountTotal")
    private BigDecimal goodAmountTotal;


}
