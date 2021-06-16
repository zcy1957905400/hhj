package com.hhj.shop.repository;

import com.hhj.shop.dto.ClassifyAllDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.global.ResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassifyRepository {
    int delete(Integer id);

    int insert(Classify classify);

    int update(Classify classify);

    Classify findById(Integer id);

    int findByNameWithCount(String clsName);

    List<Classify> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    int batchUpdate(@Param("classifyList") List<Classify> classifyList);

    int batchInsert(@Param("classifyList") List<Classify> classifyList);

    List<Classify> findAllClassify();
}
