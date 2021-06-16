package com.hhj.shop.service;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.History;
import com.hhj.shop.global.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HistoryService {
    ResultVO<ClassifyDTO> delete(Integer id);

    ResultVO<String> insert(History history);

    ResultVO<String> update(History history);

    ResultVO<History> findById(Integer id);

    ResultVO<List<HistoryAddresssDTO>> findByHistory(History history);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    ResultVO<String> batchUpdate(History history);


}
