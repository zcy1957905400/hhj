package com.hhj.shop.controller;

import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.ShopcartService;
import com.hhj.shop.service.StatisticsService;
import com.hhj.shop.util.NotResponseBody;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "数据统计模块")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {


    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "折线图数据")
    @GetMapping("/LineChart")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<List<String>>> LineChart() {
        List<List<String>> LineChart = statisticsService.findByOrderSales();
        return new ResultVO<>(ResultCode.SUCCESS, LineChart);
    }

    @ApiOperation(value = "数据统计")
    @GetMapping("/dataStatistics")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<String>> dataStatistics() {
        List<String> dataStatistics = statisticsService.dataStatistics();
        return new ResultVO<>(ResultCode.SUCCESS, dataStatistics);
    }
}
