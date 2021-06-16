package com.hhj.shop.controller;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.service.impl.GoodsServiceImpl;
import com.hhj.shop.util.ImageUploadUtil;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = "商品模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    protected static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @ApiImplicitParams({
            @ApiImplicitParam(name="goodName",value="商品名",required=true),
            @ApiImplicitParam(name="inventory",value="库存",required=true,dataType="Integer"),
            @ApiImplicitParam(name="price",value="价格",required=true,dataType="BigDecimal"),
            @ApiImplicitParam(name="photos",value="首页图片",required=true),
            @ApiImplicitParam(name="particulars",value="详情",required=true),
            //@ApiImplicitParam(name="sales",value="已销售数",required=true,dataType="Integer"),
            @ApiImplicitParam(name="carousel",value="是否轮播，默认为0，不轮播，1：轮播",required=true,dataType="Integer"),
            @ApiImplicitParam(name="clsId",value="分类id",required=true,dataType="Integer"),
            @ApiImplicitParam(name="spec",value="规格",required=true),
            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer")
    })
    @ApiOperation(value = "添加分类")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> add(@Valid @RequestBody Goods goods, BindingResult bindingResult) {
        System.out.println(goods);
        return goodsService.insert(goods);
    }

    @RequestMapping(value="/imageUpload",method={RequestMethod.GET,RequestMethod.POST})
    public ResultVO<ImageDTO> excel(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/admin/upload/images";
        path=path.substring(1,path.length());
        String imagepath=ImageUploadUtil.uploadFile(file,path);
        //classifyService.ajaxUploadExcel(file, request, response);
        ImageDTO imageDTO=new ImageDTO();
        imageDTO.setSrc(imagepath);
        int begin=imagepath.indexOf("static");
        imageDTO.setSrc(imagepath.substring(begin,imagepath.length()));
        System.out.println(path);
        System.out.println(imagepath);
        System.out.println(imageDTO.getSrc());
        return new ResultVO<>(ResultCode.SUCCESS,imageDTO);
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
        return goodsService.findPageWithResult(pageDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="mid",value="会员ID",required=true),
            @ApiImplicitParam(name="gid",value="商品ID",required=true),
            @ApiImplicitParam(name="clsid",value="类型ID",required=true)
    })
    @ApiOperation(value = "全部商品")
    @GetMapping("/findGoods")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Goods>> goodsFindAll(@Valid ShopcartDTO shopcartDTO, BindingResult bindingResult) {
        return goodsService.findWithResult(shopcartDTO);
    }

    @ApiOperation(value = "某一栏目全部商品")
    @GetMapping("/findGoodsWithClassify")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Goods>> findGoodsWithClassify(ShopcartDTO shopcartDTO) {
        return goodsService.findGoodsWithClassify(shopcartDTO);
    }

    @ApiOperation(value = "轮播图")
    @GetMapping("/findCarousel")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Goods>> findCarousel() {
        return goodsService.findCarousel();
    }

    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        return goodsService.batchUpdate(batchDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="商品id",required=true)
    })
    @ApiOperation(value = "商品详情")
    @PostMapping("/findById")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<Goods> findById(@Valid @RequestBody Goods goods, BindingResult bindingResult) {
        return goodsService.findById(goods.getId());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="商品id",required=true),
            @ApiImplicitParam(name="goodName",value="商品名",required=true),
            @ApiImplicitParam(name="inventory",value="库存",required=true,dataType="Integer"),
            @ApiImplicitParam(name="price",value="价格",required=true,dataType="BigDecimal"),
            @ApiImplicitParam(name="photos",value="首页图片",required=true),
            @ApiImplicitParam(name="particulars",value="详情",required=true),
            //@ApiImplicitParam(name="sales",value="已销售数",required=true,dataType="Integer"),
            @ApiImplicitParam(name="carousel",value="是否轮播，默认为0，不轮播，1：轮播",required=true,dataType="Integer"),
            @ApiImplicitParam(name="clsId",value="分类id",required=true,dataType="Integer"),
            @ApiImplicitParam(name="spec",value="规格",required=true),
            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer")
    })
    @ApiOperation(value = "修改商品信息")
    @PostMapping("/update")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> update(@Valid @RequestBody Goods goods, BindingResult bindingResult) {
        System.out.println(goods);
        log.info("goods:"+goods.toString());
        return goodsService.update(goods);
    }

}
