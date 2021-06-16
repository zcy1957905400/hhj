package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Order;
import com.hhj.shop.global.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {

    ResultVO<String> insertDynamic(OrderDTO orderDTO);

    ResultVO<String> updateDynamic(Order order);

    ResultVO<Order> selectById(Order order);
    Order selectById1(Order order);
    ResultVO<List<Order>> findByIdResult(PageDTO pageDTO);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    ResultVO<String> batchUpdate(BatchDTO batchDTO);

    ResultVO<OrderTypeDTO> findByMIdOrUId(String mid,String uid);


}
