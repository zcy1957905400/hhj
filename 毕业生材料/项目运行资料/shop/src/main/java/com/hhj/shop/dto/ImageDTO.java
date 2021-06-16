package com.hhj.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class ImageDTO {

    private String src;
    private String path;
}
