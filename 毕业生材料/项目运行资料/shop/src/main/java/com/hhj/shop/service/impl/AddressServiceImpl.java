package com.hhj.shop.service.impl;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.RegDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.Address;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.AddressRepository;
import com.hhj.shop.service.AddressService;
import com.hhj.shop.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    protected static Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        List<Address> addressIdList=new ArrayList<>();
        for (Integer i : batchDTO.getIds() ) {
            Address address=new Address();
            address.setId(i);
            if (batchDTO.getStatus()==1)
                address.setType(1);
            else address.setType(0);
            addressIdList.add(address);
        }
        log.info("请求值："+addressIdList.toString());
        int i= addressRepository.batchUpdate(addressIdList);
        log.info("影响行数："+i);
        return new ResultVO<>(ResultCode.SUCCESS,null);
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords= pageDTO.getKeywords();
        if(keywords!=null&&keywords!=""){
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount =addressRepository.findPageWithCount(pageDTO);
        //计算共多少页
        if (pageDTO.getLimit()!=null&&pageDTO.getPage()!=null){
            int pageSize = pageDTO.getLimit();
            pageDTO.setPage((pageDTO.getPage()-1)*pageSize);
            int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        }
        System.out.println(pageDTO);
        List<Address> addressList=addressRepository.findPageWithResult(pageDTO);
        log.info("addres-uid:"+addressList.get(0).getUserId());
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setData(addressList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (addressList.size()!=0) {
            resultDTO.setCode(0);
            log.info("总数："+totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(addressList);
            return resultDTO;
        }else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public ResultVO<List<RegDTO>> findRegWithCount() {
        List<RegDTO> regDTOList=addressRepository.findRegWithCount();
        List<RegDTO> regDTOList1=new ArrayList<>();
        String[] p={"南海诸岛","北京","天津","上海","重庆","河北","河南","云南","辽宁",
                "黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北","广西",
                "甘肃","山西","内蒙古","陕西","吉林","福建","贵州","广东","青海","西藏",
                "四川","宁夏","海南","台湾","香港","澳门"};
        for (String p1:p){
            int i=(int)(Math.random() * (1000-100+1) + 100);
            for (RegDTO regDTO:regDTOList){
                RegDTO regDTO1=new RegDTO();
                if (regDTO.getName().equals(p1)) {
                    regDTO1.setName(regDTO.getName());
                    regDTO1.setValue(regDTO.getValue()+i);
                }else {
                    regDTO1.setName(p1);
                    regDTO1.setValue(0);
                }
                regDTOList1.add(regDTO1);
            }
        }
        return new ResultVO<>(ResultCode.SUCCESS,regDTOList1);
    }
}
