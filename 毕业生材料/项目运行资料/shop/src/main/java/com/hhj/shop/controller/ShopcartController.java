package com.hhj.shop.controller;

import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.Shopcart;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.service.ShopcartService;
import com.hhj.shop.service.uploadZipService;
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

@Api(tags = "购物车管理模块")
@RestController
@RequestMapping("/shopcart")
public class ShopcartController {

    @Autowired
    private ShopcartService shopcartService;
    @Autowired
    private GoodsService goodsService;


    @ApiImplicitParams({
            @ApiImplicitParam(name="clsName",value="分类名",required=true),
            @ApiImplicitParam(name="synopsis",value="简介",required=true),
            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer")
    })
    @ApiOperation(value = "添加分类")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> add(@Valid @RequestBody Shopcart shopcart, BindingResult bindingResult) {
        return shopcartService.insert(shopcart);
    }

    @ApiOperation(value = "某一购物车全部商品")
    @GetMapping("/findGoodsWithCart")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Goods>> findGoodsWithCart(ShopcartDTO shopcartDTO) {
        return shopcartService.findWithResult(shopcartDTO);
    }

}
