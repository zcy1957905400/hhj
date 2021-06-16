package com.hhj.shop.controller;

import com.hhj.shop.dto.AllDTO;
import com.hhj.shop.dto.ClassifyAllDTO;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.RegionRepository;
import com.hhj.shop.service.OrderService;
import com.hhj.shop.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "数据统计模块")
@RestController
@RequestMapping("/Region")
public class RegionController {

    @Resource
    protected RegionService regionService;

    @ApiOperation(value = "分类id和名称")
    @PostMapping("/findAllRegion")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<AllDTO>> findAllRegion() {
        return regionService.findAllRegion();
    }
}
