package com.hhj.shop.controller;


import com.hhj.shop.dto.HistoryAddresssDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.History;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.HistoryService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "历史自提点模块")
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true)
    })
    @ApiOperation(value = "历史自提点查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO historyByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return historyService.findPageWithResult(pageDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="mid",value="用户id",required=true)
    })
    @ApiOperation(value = "某一用户历史自提点查询")
    @GetMapping("/findByHistory")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<HistoryAddresssDTO>> findByHistory(@Valid History history, BindingResult bindingResult) {
        System.out.println(history.toString());
        return historyService.findByHistory(history);
    }

    @ApiOperation(value = "修改自提点")
    @PostMapping("/batchUpdate")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody History history){
        return  historyService.batchUpdate(history);
    }

    @ApiOperation(value = "添加自提点")
    @PostMapping("/add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> insert(@RequestBody History history){
        return  historyService.insert(history);
    }

}
