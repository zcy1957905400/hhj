package com.hhj.shop.service.impl;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Address;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.AddressRepository;
import com.hhj.shop.repository.ClassifyRepository;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.repository.UserRepository;
import com.hhj.shop.service.ClassifyService;
import com.hhj.shop.service.UserService;
import com.hhj.shop.util.DateUtil;
import com.hhj.shop.util.ExcelUtils;
import com.hhj.shop.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    protected static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    //    @Override
//    public ResultVO<User> delete(Integer id) {
//        ClassifyDTO classifyDTO = new ClassifyDTO();
//        classifyDTO.setId(id);
//        int result = classifyRepository.delete(id);
//        if (result > 0)
//            return new ResultVO<>(ResultCode.SUCCESS, classifyDTO);
//        else
//            return new ResultVO<>(ResultCode.ERROR, null);
//    }
//
    @Override
    public ResultVO<String> insert(UserDTO userDTO) {
        User user = new User();
        user.setPhone(userDTO.getPhone());
        user.setName(userDTO.getName());
        user.setPassword(MD5Util.string2MD5(userDTO.getPassword()));
        user.setPortrait(userDTO.getPortrait());
        String str1 = "0.00";
        BigDecimal bd = new BigDecimal(str1);
        user.setLeaveAmount(bd);
        user.setType(0);
        user.setStatus(0);
        int count = userRepository.findByPhoneWithCount(user.getPhone());
        System.out.println(count);
        if (count == 0) {
            int result = userRepository.insert(user);
            if (result > 0) {
                User user1 = userRepository.findByPhone(user.getPhone());
                Address address = new Address();
                address.setUserId(user1.getId());
                address.setName(userDTO.getAdd_name());
                address.setType(0);
                address.setDetailed(userDTO.getDetailed());
                address.setRegId(userDTO.getRegId());
                log.info("address:" + address.toString());
                int result1 = addressRepository.insert(address);
                log.info("result1:" + result1);
                if (result1 > 0) {
                    log.info("成功");
                    return new ResultVO<>(ResultCode.SUCCESS, null);
                } else
                    return new ResultVO<>(ResultCode.ERROR, null);

            } else
                return new ResultVO<>(ResultCode.ERROR, null);
        } else
            return new ResultVO<>(ResultCode.MEMBER_FAIL, null);
    }

    @Override
    public ResultVO<String> update(User user) {
        user.setPassword(MD5Util.string2MD5(user.getPassword()));
        int result = userRepository.updateDynamic(user);
        if (result > 0) {
            return new ResultVO<>(ResultCode.SUCCESS, null);
        }
        return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<String> updateUserAddresss(UserDTO userDTO) {
        User user = new User();
        user.setPhone(userDTO.getPhone());
        user.setName(userDTO.getName());
        user.setPassword(MD5Util.string2MD5(userDTO.getPassword()));
        user.setPortrait(userDTO.getPortrait());
        int result = userRepository.updateDynamic(user);
        if (result > 0) {
            User user1 = userRepository.findByPhone(user.getPhone());
            Address address =addressRepository.selectByUId(user1.getId());
            address.setUserId(user1.getId());
            address.setName(userDTO.getAdd_name());
            address.setDetailed(userDTO.getDetailed());
            address.setRegId(userDTO.getRegId());
            log.info("address:" + address.toString());
            int result1 = addressRepository.updateDynamic(address);
            log.info("result1:" + result1);
            if (result1 > 0) {
                log.info("成功");
                return new ResultVO<>(ResultCode.SUCCESS, null);
            } else
                return new ResultVO<>(ResultCode.ERROR, null);

        } else
            return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public User findById(Integer id) {
        User user1 = userRepository.selectByLeaveAmount(id);
        return user1;
    }

    @Override
    public UserDTO findByUser(Integer id) {
        UserDTO userDTO = userRepository.selectByUser(id);
//        if (user1.getLeaveAmount()==null){
//            User user=userRepository.selectById(id);
//            userRepository.updateDynamic(user);
//            return user;
//        }else{
        log.info(userDTO.toString());
        return userDTO;
    }
//
//    @Override
//    public ResultVO<String> update(User user) {
//        Long startTs = System.currentTimeMillis()/1000; // 当前时间戳
//        classify.setUpdatetime(String.valueOf(startTs));
//        int result = classifyRepository.update(classify);
////        List<Goods> goodsIdList=goodsRepository.findGoodsWithClassify(classify.getId());
////        for (Goods goods:goodsIdList ) {
////            if (classify.getType()==1)
////                goods.setType(1);
////            else goods.setType(0);
////        }
////        log.info(goodsIdList.toString());
////        goodsRepository.batchUpdate(goodsIdList);
//        if (result > 0)
//            return new ResultVO<>(ResultCode.SUCCESS, null);
//        else
//            return new ResultVO<>(ResultCode.ERROR, null);
//    }
//
//    @Override
//    public ResultVO<User> findById(Integer id) {
//        Classify classify=classifyRepository.findById(id);
//        if (classify!=null) {
//            return new ResultVO<>(ResultCode.SUCCESS, classify);
//        }else
//            return new ResultVO<>(ResultCode.CLASSIFY_FAIL,null);
//    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords = pageDTO.getKeywords();
        if (keywords != null && keywords != "") {
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount = userRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage() - 1) * pageSize);
        //int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        System.out.println(pageDTO);
        List<User> userList = userRepository.findPageWithResult(pageDTO);
        ResultDTO resultDTO = new ResultDTO();
        //resultDTO.setData(userList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (userList.size() != 0) {
            resultDTO.setCode(0);
            log.info("总数：" + totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(userList);
            return resultDTO;
        } else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }


    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        List<User> userList = new ArrayList<>();
        for (Integer i : batchDTO.getIds()) {
            User user = new User();
            user.setId(i);
            user.setStatus(batchDTO.getStatus());
            userList.add(user);
            //修改商品状态
            Address address = new Address();
            address.setUserId(user.getId());
            List<Address> addressIdList = addressRepository.findAddressWithUser(address);
            if (addressIdList.size() != 0) {
                BatchDTO batchDTO1 = new BatchDTO();
                for (Address address1 : addressIdList) {
                    if (user.getStatus() == 1)
                        address1.setType(1);
                    else address1.setType(0);

                }
                log.info("addressIdList:" + addressIdList.toString());
                addressRepository.batchUpdate(addressIdList);
            }
        }
        userRepository.batchUpdate(userList);
        return new ResultVO<>(ResultCode.SUCCESS, null);
    }

}
