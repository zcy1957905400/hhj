package com.hhj.shop.repository;

import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    int delete(Integer id);

    int insert(Member member);

    int insertDynamic(Member member);

    int updateDynamic(Member member);

    int update(Member member);

    Member login(Member member);

    int findByPhoneWithCount(String phone);

    Member selectById(Integer id);

    List<Member> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

}
