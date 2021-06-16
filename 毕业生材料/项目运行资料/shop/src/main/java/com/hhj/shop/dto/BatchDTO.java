package com.hhj.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
@Setter
public class BatchDTO {

    @NotNull
    @JsonProperty(value = "ids")
    private List<Integer> ids;

    @NotNull
    @JsonProperty(value = "oids")
    private List<String> oids;
    @NotNull
    @JsonProperty(value = "type")
    private Integer type;
    @NotNull
    @JsonProperty(value = "carousel")
    private Integer carousel;
    @NotNull
    @JsonProperty(value = "status")
    private Integer status;
}
