package com.hhj.shop.repository;

import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.StatisticsDTO;
import com.hhj.shop.dto.WithdrawalDTO;
import com.hhj.shop.entity.User;
import com.hhj.shop.entity.Withdrawal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WithdrawalRepository {
	int delete(Integer id);

	int insert(Withdrawal withdrawal);

	int insertDynamic(Withdrawal withdrawal);

	int updateDynamic(Withdrawal withdrawal);

	int update(Withdrawal withdrawal);

	Withdrawal selectById(Integer id);

	List<Withdrawal> selectByUId(Integer uId);

	List<WithdrawalDTO> findPageWithResult(PageDTO pageDTO);

	Integer findPageWithCount(PageDTO pageDTO);

	int batchUpdate(@Param("withdrawalList") List<Withdrawal> withdrawalList);

	List<StatisticsDTO> selectByWithdrawalSales(@Param("pastTimeStr") String pastTimeStr, @Param("currenttimeStr") String currenttimeStr);
}
