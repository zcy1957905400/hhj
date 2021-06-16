package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.global.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ClassifyService {
    ResultVO<ClassifyDTO> delete(Integer id);

    ResultVO<String> insert(Classify classify);

    ResultVO<String> update(Classify classify);

    ResultVO<Classify> findById(Integer id);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    ResultVO<String> batchUpdate(BatchDTO batchDTO);

    String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

    ResultVO<List<ClassifyAllDTO>> findAllClassify() ;

}
