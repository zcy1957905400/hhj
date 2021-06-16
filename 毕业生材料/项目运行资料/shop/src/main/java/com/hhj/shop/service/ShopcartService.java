package com.hhj.shop.service;

import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.Shopcart;
import com.hhj.shop.global.ResultVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ShopcartService {

    int delete(Integer id);

    ResultVO<String> insert(Shopcart shopcart);

    int insertDynamic(Shopcart shopcart);

    int updateDynamic(Shopcart shopcart);

    int update(Shopcart shopcart);

    Shopcart selectById(Integer id);

    List<Shopcart> findPageWithResult(Shopcart shopcart);

    ResultVO<List<Goods>> findWithResult(ShopcartDTO shopcartDTO);

    Integer findPageWithCount(Shopcart shopcart);

}
