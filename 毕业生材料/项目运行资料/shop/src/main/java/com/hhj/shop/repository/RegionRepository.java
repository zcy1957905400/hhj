package com.hhj.shop.repository;

import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RegionRepository {
    int delete(Integer id);

    int insert(Region region);

    int insertDynamic(Region region);

    int updateDynamic(Region region);

    int update(Region region);

    Region selectById(Integer id);

    List<Region> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    List<Region> findAllRegion();
}
