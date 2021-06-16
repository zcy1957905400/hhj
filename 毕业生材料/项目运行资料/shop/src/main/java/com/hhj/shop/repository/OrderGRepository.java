package com.hhj.shop.repository;

import com.hhj.shop.dto.GoodsDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.OrderG;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OrderGRepository {
//    int delete(Integer id);
//
//    int insert(OrderG orderG);
//
//    int insertDynamic(OrderG orderG);
//
//    int updateDynamic(OrderG orderG);
//
//    int update(OrderG orderG);
//
    OrderG selectById(@Param("id")String id);
//
//    List<OrderG> findPageWithResult(OrderGDTO orderGDTO);
//
//    Integer findPageWithCount(OrderGDTO orderGDTO);

    int batchInsert(@Param("orderGList") List<OrderG> orderGList);

    List<GoodsDTO> findGoodsWithOrder(@Param("orderG") OrderG orderG);
}