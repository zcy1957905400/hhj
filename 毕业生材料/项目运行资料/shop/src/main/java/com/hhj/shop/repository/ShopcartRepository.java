package com.hhj.shop.repository;

import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.Order;
import com.hhj.shop.entity.Shopcart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopcartRepository {

    int delete(Integer id);

    int batchDel(@Param("mId")Integer mId, @Param("ids")Integer[] idList);

    int insert(Shopcart shopcart);

    int insertDynamic(Shopcart shopcart);

    int updateDynamic(Shopcart shopcart);

    int update(Shopcart shopcart);

    Shopcart selectById(Integer id);

    Shopcart selectByShopcart(Shopcart shopcart);

    List<Shopcart> findByShopcart(@Param("mId")Integer mId, @Param("ids")Integer[] idList);

    List<Shopcart> findPageWithResult(Shopcart shopcart);

    Integer findPageWithCount(Shopcart shopcart);

}
