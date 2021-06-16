package com.hhj.shop.service.impl;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.History;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.AddressRepository;
import com.hhj.shop.repository.HistoryRepository;
import com.hhj.shop.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    protected static Logger log = LoggerFactory.getLogger(HistoryServiceImpl.class);

    @Override
    public ResultVO<ClassifyDTO> delete(Integer id) {

        return null;
    }

    @Override
    public ResultVO<String> insert(History history) {
        history.setType(1);
        List<History> result=historyRepository.selectByMIdUId(history);
        if (result.size()!=0){
            History history1=historyRepository.selectBymId(history.getMId());
            if (history1!=null){
                history1.setType(0);
                historyRepository.updateDynamic(history1);
            }
            History history2=new History();
            for (History history3:result){
                history2=history3;
            }
            history.setId(history2.getId());
            historyRepository.updateDynamic(history);
        }else{
            History history1=historyRepository.selectBymId(history.getMId());
            if (history1!=null){
                history1.setType(0);
                historyRepository.updateDynamic(history1);
            }
            historyRepository.insertDynamic(history);
        }
        return new ResultVO<>(ResultCode.SUCCESS,null);
    }

    @Override
    public ResultVO<String> update(History history) {
        return null;
    }

    @Override
    public ResultVO<History> findById(Integer id) {
        return null;
    }

    @Override
    public ResultVO<List<HistoryAddresssDTO>> findByHistory(History history) {
        return new ResultVO<>(ResultCode.SUCCESS,historyRepository.selectByHistory(history));
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords= pageDTO.getKeywords();
        if(keywords!=null&&keywords!=""){
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount =historyRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage()-1)*pageSize);
        //int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
        System.out.println(pageDTO);
        List<History> userList=historyRepository.findPageWithResult(pageDTO);
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setData(userList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (userList.size()!=0) {
            resultDTO.setCode(0);
            log.info("总数："+totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(userList);
            return resultDTO;
        }else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public ResultVO<String> batchUpdate(History history) {
        History history1=historyRepository.selectBymId(history.getMId());
        if (history1!=null){
            history1.setType(0);
            historyRepository.updateDynamic(history1);
        }
        history.setType(1);
        historyRepository.updateDynamic(history);
        return new ResultVO<>(ResultCode.SUCCESS,null);
    }
}
