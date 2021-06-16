package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.User;
import com.hhj.shop.entity.Withdrawal;
import com.hhj.shop.global.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface WithdrawalService {
    int delete(Integer id);

    ResultVO<String> insert(Withdrawal withdrawal);

    ResultVO<String> insertDynamic(Withdrawal withdrawal);

    int updateDynamic(Withdrawal withdrawal);

    int update(Withdrawal withdrawal);

    Withdrawal selectById(Integer id);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    ResultVO<String> batchUpdate(BatchDTO batchDTO);

    ResultVO<List<Withdrawal>> selectByUId(Withdrawal withdrawal);
}