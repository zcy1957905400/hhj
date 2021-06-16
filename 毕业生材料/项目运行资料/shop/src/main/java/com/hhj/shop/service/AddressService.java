package com.hhj.shop.service;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.RegDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.global.ResultVO;

import java.util.List;

public interface AddressService {

    ResultVO<String> batchUpdate(BatchDTO batchDTO);

    ResultDTO findPageWithResult(PageDTO pageDTO);

    ResultVO<List<RegDTO>> findRegWithCount();
}
