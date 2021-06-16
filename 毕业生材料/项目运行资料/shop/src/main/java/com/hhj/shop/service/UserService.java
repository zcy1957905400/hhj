package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
//    ResultVO<User> delete(Integer id);
//
    ResultVO<String> insert(UserDTO userDTO);
//
    ResultVO<String> update(User user);

    ResultVO<String> updateUserAddresss(UserDTO userDTO);
//
    User findById(Integer id);

    UserDTO findByUser(Integer id);

    ResultDTO findPageWithResult(PageDTO pageDTO);
//
    ResultVO<String> batchUpdate(BatchDTO batchDTO);

}
