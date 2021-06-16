package com.hhj.shop.service.impl;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.User;
import com.hhj.shop.entity.Withdrawal;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.LoginRepository;
import com.hhj.shop.repository.UserRepository;
import com.hhj.shop.repository.WithdrawalRepository;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.service.WithdrawalService;
import com.hhj.shop.util.DateUtil;
import com.hhj.shop.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private UserRepository userRepository;

    protected static Logger log = LoggerFactory.getLogger(WithdrawalServiceImpl.class);


    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public ResultVO<String> insert(Withdrawal withdrawal) {
        log.info("uid:"+withdrawal.getUId());
        Long startTs = System.currentTimeMillis()/1000; // 当前时间戳
        withdrawal.setCreatetime(String.valueOf(startTs));
        withdrawal.setStatus(0);
        int result=withdrawalRepository.insertDynamic(withdrawal);
        if (result>0){
            User user=userRepository.selectByLeaveAmount(withdrawal.getUId());
            user.setLeaveAmount(user.getLeaveAmount().subtract(withdrawal.getAmount()));
            userRepository.updateDynamic(user);
            return new ResultVO<>(ResultCode.SUCCESS,null);
        }
        return new ResultVO<>(ResultCode.ERROR,null);
    }

    @Override
    public ResultVO<String> insertDynamic(Withdrawal withdrawal) {
        return new ResultVO<>(ResultCode.SUCCESS,null);
    }

    @Override
    public int updateDynamic(Withdrawal withdrawal) {
        return 0;
    }

    @Override
    public int update(Withdrawal withdrawal) {
        return 0;
    }

    @Override
    public Withdrawal selectById(Integer id) {
        return null;
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
        int totalCount =withdrawalRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage()-1)*pageSize);
        int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        System.out.println(pageDTO);
        List<WithdrawalDTO> withdrawalDTOList=withdrawalRepository.findPageWithResult(pageDTO);
        for ( WithdrawalDTO withdrawalDTO:withdrawalDTOList) {
            String createDate = DateUtil.timeStamp2Date(withdrawalDTO.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
            //String updateDate = DateUtil.timeStamp2Date(classify.getUpdatetime(), "yyyy-MM-dd HH:mm:ss");
            withdrawalDTO.setCreatetime(createDate);
        }
        ResultDTO resultDTO=new ResultDTO();
        //resultDTO.setData(commissionDTOList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (withdrawalDTOList.size()!=0) {
            resultDTO.setCode(0);
            log.info("总数："+totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(withdrawalDTOList);
            return resultDTO;
        }else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public Integer findPageWithCount(PageDTO pageDTO) {
        return null;
    }

    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        List<Withdrawal> withdrawalList=new ArrayList<>();
        for (Integer i : batchDTO.getIds()) {
            Withdrawal withdrawal=new Withdrawal();
            withdrawal.setId(i);
            withdrawal.setStatus(batchDTO.getStatus());
            withdrawalList.add(withdrawal);
            if (batchDTO.getStatus()==2){
                Withdrawal withdrawal1=withdrawalRepository.selectById(i);
                User user=userRepository.selectByLeaveAmount(withdrawal1.getUId());
                log.info(user.getLeaveAmount()+"----"+withdrawal1.getAmount());
                user.setLeaveAmount(user.getLeaveAmount().add(withdrawal1.getAmount()));
                userRepository.updateDynamic(user);
            }
        }
        int result=withdrawalRepository.batchUpdate(withdrawalList);
        if (result>0){
            return new ResultVO<>(ResultCode.SUCCESS,null);
        }else {
            return new ResultVO<>(ResultCode.ERROR,null);
        }

    }

    @Override
    public ResultVO<List<Withdrawal>> selectByUId(Withdrawal withdrawal) {
        List<Withdrawal> withdrawalList=withdrawalRepository.selectByUId(withdrawal.getUId());
        if (withdrawalList.size()!=0){
            return new ResultVO<>(ResultCode.SUCCESS,withdrawalList);
        }else{
            return new ResultVO<>(ResultCode.ERROR,null);
        }

    }
}
