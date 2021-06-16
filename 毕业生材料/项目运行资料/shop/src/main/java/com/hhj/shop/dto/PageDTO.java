package com.hhj.shop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PageDTO {

    private Integer page;
    private Integer limit;
    private String startTime;
    private String endTime;
    private String  keywords;
    private String  mid;
    private String  uid;
    private String  status;
}
