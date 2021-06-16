package com.hhj.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class ShopcartDTO {
    @NotNull
    @JsonProperty(value = "mid")
    private Integer mid;
    @NotNull
    @JsonProperty(value = "gid")
    private Integer gid;

    @NotNull
    @JsonProperty(value = "clsid")
    private Integer clsid;

    @NotNull
    @JsonProperty(value = "keywords")
    private String keywords;
}
