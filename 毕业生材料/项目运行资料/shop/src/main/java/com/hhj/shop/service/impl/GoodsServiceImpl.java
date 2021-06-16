package com.hhj.shop.service.impl;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.util.DateUtil;
import com.hhj.shop.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    protected static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Override
    public ResultVO<Goods> delete(Integer id) {
        return null;
    }

    @Override
    public ResultVO<String> insert(Goods goods) {
        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
        goods.setSales(0);
        goods.setCreatetime(String.valueOf(startTs));
        int result = goodsRepository.insert(goods);
        if (result > 0)
            return new ResultVO<>(ResultCode.SUCCESS, null);
        else
            return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<String> update(Goods goods) {

        int result = goodsRepository.update(goods);
        if (result == 1) {
            return new ResultVO<>(ResultCode.SUCCESS, String.valueOf(result));
        } else
            return new ResultVO<>(ResultCode.ERROR, String.valueOf(result));
    }


    @Override
    public ResultVO<Goods> findById(Integer id) {
        Goods goods = goodsRepository.findById(id);
        if (goods != null) {
            return new ResultVO<>(ResultCode.SUCCESS, goods);
        } else
            return new ResultVO<>(ResultCode.CLASSIFY_FAIL, null);
    }

    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords = pageDTO.getKeywords();
        String startTime = pageDTO.getStartTime();
        String endTime = pageDTO.getEndTime();
        if (startTime != null && startTime != "" && endTime != null && endTime != "") {
            pageDTO.setStartTime(DateUtil.date2TimeStamp(startTime, "yyyy-MM-dd"));
            endTime = endTime + " " + "23:59:59";
            System.out.println(endTime);
            pageDTO.setEndTime(DateUtil.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss"));
            System.out.println(pageDTO.getEndTime());
        }
        if (keywords != null && keywords != "") {
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount = goodsRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage() - 1) * pageSize);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        System.out.println(pageDTO);
        List<Goods> goodsList = goodsRepository.findPageWithResult(pageDTO);
        for (Goods goods : goodsList) {
            String createDate = DateUtil.timeStamp2Date(goods.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
            goods.setCreatetime(createDate);
        }
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(goodsList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (goodsList.size() != 0) {
            resultDTO.setCode(0);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(goodsList);
            return resultDTO;
        } else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public ResultVO<List<Goods>> findWithResult(ShopcartDTO shopcartDTO) {
        //共多少个类型
        PageDTO pageDTO = new PageDTO();
        int totalCount = goodsRepository.findPageWithCount(pageDTO);
        pageDTO.setPage(0);
        pageDTO.setLimit(totalCount);
        pageDTO.setStatus("1");
        if (shopcartDTO.getMid() == null) {
            List<Goods> goodsList = goodsRepository.findPageWithResult(pageDTO);
            if (goodsList.size() != 0) {
                return new ResultVO<>(ResultCode.SUCCESS, goodsList);
            }
        } else {
            if (shopcartDTO.getKeywords() != null && shopcartDTO.getKeywords() != "") {
                pageDTO.setKeywords('%' + shopcartDTO.getKeywords() + '%');
            }
            List<Goods> goodsList = goodsRepository.findPageWithResult(pageDTO);
            List<Goods> goodsList1 = goodsRepository.findWithResult(shopcartDTO);
            log.info("goodslist1:" + goodsList1.toString());
            for (Goods goods : goodsList) {
                for (Goods goods1 : goodsList1) {
                    if (goods.getId().equals(goods1.getId())) {
                        //goods.setStatus(1);
                        //goods.setNumber(0);
                        goods.setNumber(goods1.getNumber());
                        log.info("goods:" + goods.toString());
                    }
                }
            }
            log.info("goodslist:" + goodsList.toString());
            return new ResultVO<>(ResultCode.SUCCESS, goodsList);
        }
        return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<List<Goods>> findGoodsWithClassify(ShopcartDTO shopcartDTO) {
        //共多少个类型
        PageDTO pageDTO = new PageDTO();
        int totalCount = goodsRepository.findPageWithCount(pageDTO);
        pageDTO.setPage(0);
        pageDTO.setLimit(totalCount);
        Goods goods = new Goods();
        goods.setClsId(shopcartDTO.getClsid());
        List<Goods> goodsList1 = goodsRepository.findWithResult(shopcartDTO);
        if (shopcartDTO.getMid() != null) {
            List<Goods> goodsList = goodsRepository.findGoodsWithClassify(goods);
            for (Goods goods1 : goodsList) {
                for (Goods goods2 : goodsList1) {
                    if (goods1.getId().equals(goods2.getId())) {
                        //goods.setStatus(1);
                        //goods.setNumber(0);
                        goods1.setNumber(goods2.getNumber());
                        log.info("goods:" + goods1.toString());
                    }
                }

            }
            log.info("goodslist1:" + goodsList.toString());
            if (goodsList.size() != 0) {
                return new ResultVO<>(ResultCode.SUCCESS, goodsList);
            }
        } else {
            List<Goods> goodsList = goodsRepository.findGoodsWithClassify(goods);
            if (goodsList.size() != 0) {
                return new ResultVO<>(ResultCode.SUCCESS, goodsList);
            }
        }

        return new ResultVO<>(ResultCode.ERROR, null);
    }

    @Override
    public ResultVO<List<Goods>> findCarousel() {
        List<Goods> goodsList=goodsRepository.findCarousel();
        return new ResultVO<>(ResultCode.SUCCESS,goodsList);
    }

    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        List<Goods> goodsList = new ArrayList<>();
        for (Integer i : batchDTO.getIds()) {
            Goods goods = new Goods();
            goods.setId(i);
            goods.setType(batchDTO.getType());
            goods.setCarousel(batchDTO.getCarousel());
            goodsList.add(goods);
        }
        log.info("请求值：" + goodsList.toString());
        int i = goodsRepository.batchUpdate(goodsList);
        log.info("影响行数：" + i);
        return new ResultVO<>(ResultCode.SUCCESS, null);
    }

    @Override
    public String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
