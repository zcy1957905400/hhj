package com.hhj.shop.repository;

import com.hhj.shop.dto.ClassifyAllDTO;
import com.hhj.shop.dto.GoodsDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.OrderG;
import com.hhj.shop.global.ResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsRepository {
    int delete(Integer id);

    int insert(Goods goods);

    int insertDynamic(Goods goods);

    int updateDynamic(Goods goods);

    int update(Goods goods);

    Goods findById(Integer id);

    int batchInsert(@Param("goodsList") List<Goods> goodsList);

    int batchUpdate(@Param("goodsList") List<Goods> goodsList);

    List<Goods> selectDataList(@Param("goodsList") List<Goods> goodsList);

    int updateDynamic1(@Param("goodsList") List<Goods> goodsList);

    List<Goods> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    List<Goods> findWithResult(ShopcartDTO shopcartDTO);

    Integer findWithCount();

    List<Goods> findGoodsWithClassify(Goods goods);

    List<Goods> findCarousel();

    //List<GoodsDTO> findGoodsWithOrder(@Param("orderG") OrderG orderG);

}
