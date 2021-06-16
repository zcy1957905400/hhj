package com.hhj.shop.repository;

import com.hhj.shop.dto.HistoryAddresssDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface HistoryRepository {
    int delete(Integer id);

    int insert(History history);

    int insertDynamic(History history);

    int updateDynamic(History history);

    int update(History history);

    History selectById(Integer id);

    History selectBymId(Integer mid);

    List<HistoryAddresssDTO> selectByHistory(History history);

    List<History> selectByMIdUId(History history);

    List<History> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);
}
