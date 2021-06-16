package com.hhj.shop.service.impl;

import com.hhj.shop.dto.CommissionDTO;
import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Commission;
import com.hhj.shop.entity.User;
import com.hhj.shop.repository.CommissionRepository;
import com.hhj.shop.repository.LoginRepository;
import com.hhj.shop.service.CommissionService;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.util.DateUtil;
import com.hhj.shop.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    protected static Logger log = LoggerFactory.getLogger(CommissionServiceImpl.class);


    @Override
    public List<CommissionDTO> selectByuId(Integer id) {
        return commissionRepository.selectByuId(id);
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords= pageDTO.getKeywords();
        String startTime= pageDTO.getStartTime();
        String endTime= pageDTO.getEndTime();
        if(startTime!=null&&startTime!=""&&endTime!=null&&endTime!="") {
            pageDTO.setStartTime(DateUtil.date2TimeStamp(startTime, "yyyy-MM-dd"));
            endTime=endTime+" "+"23:59:59";
            System.out.println(endTime);
            pageDTO.setEndTime(DateUtil.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss"));
            System.out.println(pageDTO.getEndTime());
        }
        if(keywords!=null&&keywords!=""){
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount =commissionRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage()-1)*pageSize);
        int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        System.out.println(pageDTO);
        List<CommissionDTO> commissionDTOList=commissionRepository.findPageWithResult(pageDTO);
        for ( CommissionDTO commissionDTO:commissionDTOList) {
            String createDate = DateUtil.timeStamp2Date(commissionDTO.getOrder_settlement_time(), "yyyy-MM-dd HH:mm:ss");
            //String updateDate = DateUtil.timeStamp2Date(classify.getUpdatetime(), "yyyy-MM-dd HH:mm:ss");
            commissionDTO.setOrder_settlement_time(createDate);
        }
        ResultDTO resultDTO=new ResultDTO();
        //resultDTO.setData(commissionDTOList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (commissionDTOList.size()!=0) {
            resultDTO.setCode(0);
            log.info("总数："+totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(commissionDTOList);
            return resultDTO;
        }else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }
}
