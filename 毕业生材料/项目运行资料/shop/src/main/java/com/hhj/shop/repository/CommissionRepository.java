package com.hhj.shop.repository;

import com.hhj.shop.dto.CommissionDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.Commission;
import com.hhj.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommissionRepository {
	int delete(Integer id);

	int insert(Commission commission);

	int insertDynamic(Commission commission);

	int updateDynamic(Commission commission);

	int update(Commission commission);

	Commission selectById(Integer id);

	List<CommissionDTO> selectByuId(Integer id);

	List<CommissionDTO> findPageWithResult(PageDTO pageDTO);

	Integer findPageWithCount(PageDTO pageDTO);
}
