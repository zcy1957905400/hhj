package com.hhj.shop.repository;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.UserDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {
    int delete(Integer id);

    int insert(User user);

    int insertDynamic(User user);

    int updateDynamic(User user);

    int update(User user);

    User selectById(Integer id);

    User selectByLeaveAmount(Integer id);

    User selectoId(String oid);

    UserDTO selectByUser(Integer id);

    int findByPhoneWithCount(String phone);

    User findByPhone(String phone);

    List<User> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    int batchUpdate(@Param("userList") List<User> userList);

}
