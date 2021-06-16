package com.hhj.shop.service;

import com.hhj.shop.dto.CommissionDTO;
import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface CommissionService {
    List<CommissionDTO> selectByuId(Integer id);
    ResultDTO findPageWithResult(PageDTO pageDTO);
}