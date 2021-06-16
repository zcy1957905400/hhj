package com.hhj.shop.repository;

import com.hhj.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginRepository {
	User login(User user);
	User loginByname(User user);
}
