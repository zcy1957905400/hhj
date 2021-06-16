package com.hhj.shop.repository;

import com.hhj.shop.dto.OrderTypeDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.StatisticsDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderRepository {
    int delete(String id);

    int insert(Order order);

    int insertDynamic(Order order);

    int updateDynamic(Order order);

    int update(Order order);

    Order selectById(String id);

    List<Order> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    int batchUpdate(@Param("orderList") List<Order> orderList);



    List<StatisticsDTO> selectByOrderSales(@Param("pastTimeStr") String pastTimeStr,@Param("currenttimeStr") String currenttimeStr);

    BigDecimal findWithAmountTotal();

    Integer findWithOrderTotal();

    Integer findWithNowOrderTotal();

    OrderTypeDTO findByMIdOrUId(@Param("mid") String mid,@Param("uid") String uid);
}