package com.hhj.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
@Setter
public class History {
    /**
     * 历史自提点id
     */
    @NotNull
    @JsonProperty(value = "id")
    private Integer id;

    /**
     * 自提点id
     */
    @NotNull
    @JsonProperty(value = "uId")
    private Integer uId;


    /**
     * 自提点id
     */
    @NotNull
    @JsonProperty(value = "adId")
    private Integer adId;

    /**
     * 用户id
     */
    @NotNull
    @JsonProperty(value = "mId")
    private Integer mId;

    /**
     * 状态，默认为0,0：禁用，1：启用
     */
    private Integer type;

    private List<Address> addressList;

    private List<User> userList;

    private List<Member> memberList;


}
