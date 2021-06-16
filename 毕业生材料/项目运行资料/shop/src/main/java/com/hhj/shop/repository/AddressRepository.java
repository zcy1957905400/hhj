package com.hhj.shop.repository;


import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.RegDTO;
import com.hhj.shop.entity.Address;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AddressRepository {
    int delete(Integer id);

    int insert(Address address);

    int insertDynamic(Address address);

    int updateDynamic(Address address);

    int update(Address address);

    Address selectById(Integer id);

    Address selectByUId(Integer id);

    List<Address> findPageWithResult(PageDTO pageDTO);

    Integer findPageWithCount(PageDTO pageDTO);

    List<Address> findAddressWithUser(Address address);

    int batchUpdate(@Param("addressList") List<Address> addressList);

    List<RegDTO> findRegWithCount();
}
