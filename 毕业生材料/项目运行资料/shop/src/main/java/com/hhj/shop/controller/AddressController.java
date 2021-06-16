package com.hhj.shop.controller;


import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.RegDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.AddressService;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "自提点模块")
@RestController
@RequestMapping("/address")
public class AddressController {

    protected static Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        return addressService.batchUpdate(batchDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true),
    })
    @ApiOperation(value = "分类查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO classifyByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return addressService.findPageWithResult(pageDTO);
    }
    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/echarts_china")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<RegDTO>> echarts_china() {
        return addressService.findRegWithCount();
    }

}
