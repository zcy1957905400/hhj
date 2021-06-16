package com.hhj.shop.service;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface GoodsService {
    ResultVO<Goods> delete(Integer id);

    ResultVO<String> insert(Goods goods);

    //int insertDynamic(Goods goods);

    //ResultVO<String> updateDynamic(Goods goods);

    ResultVO<String> update(Goods goods);

    ResultVO<Goods> findById(Integer id);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    ResultVO<List<Goods>> findWithResult(ShopcartDTO shopcartDTO);

    ResultVO<List<Goods>>  findGoodsWithClassify(ShopcartDTO shopcartDTO);

    ResultVO<List<Goods>>  findCarousel();

    //Integer findPageWithCount(PageDTO pageDTO);

    ResultVO<String> batchUpdate(BatchDTO batchDTO);

    String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);
}
