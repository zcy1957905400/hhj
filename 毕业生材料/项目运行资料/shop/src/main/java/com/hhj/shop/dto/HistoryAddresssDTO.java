package com.hhj.shop.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HistoryAddresssDTO {

    private String add_name;
    private String add_detailed;
    private String portrait;
    private Integer add_status;
    private Integer his_status;
    private Integer hisId;
}
