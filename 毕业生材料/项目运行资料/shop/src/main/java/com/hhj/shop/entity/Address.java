package com.hhj.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Address {
    /**
     * 自提点id
     */
    private Integer id;

    /**
     * 自提点名称
     */
    private String name;

    /**
     * 所属地区id
     */
    private Integer regId;

    /**
     * 所属管理员id
     */
    private Integer userId;

    /**
     * 详细地址
     */
    private String detailed;

    /**
     * 状态，默认为0,0：禁用，1：启用
     */
    private Integer type;

    /**
     * 所属地区
     */
    private List<Region> regionList;

    /**
     * 所属自提管理员
     */
    private List<User> userList;

}
