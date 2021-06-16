package com.hhj.shop.entity;

import com.hhj.shop.dto.GoodsDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Data
@Getter
@Setter
public class Order {
    /**
     * 订单id
     */
    private String  Oid;

    /**
     * 用户id
     */
    private Integer memberId;

    /**
     * 自提点管理id
     */
    private Integer adId;

    /**
     * 商品id
     */
    private Integer gId;

    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * 状态
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 自提点地址id
     */
    private Integer aId;

    /**
     * 订单结算时间
     */
    private String orderSettlementTime;

    /**
     * 订单总价
     */
    private BigDecimal goodAmountTotal;

    private List<OrderG> OrderGList;

    private List<User> userList;
    private List<Address> addressList;
    private List<Member> memberList;

    private List<GoodsDTO> goodsDTOList;

    public Order(){

    }
}