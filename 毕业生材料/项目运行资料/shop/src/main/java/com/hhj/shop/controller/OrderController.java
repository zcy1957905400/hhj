package com.hhj.shop.controller;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Order;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.OrderService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author hhj
 * @description order
 * @date 2021-04-18
 */
@Api(tags = "订单管理模块")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    protected OrderService orderService;


    //    /**
//     * 新增
//     * @author hhj
//     * @date 2021/04/18
//     **/
//    @RequestMapping("/insert")
//    public Object insert(Order order){
//        return orderService.insert(order);
//    }
//
////    /**
////     * 刪除
////     * @author hhj
////     * @date 2021/04/18
////     **/
////    @RequestMapping("/delete")
////    public ReturnT<String> delete(int id){
////        return orderService.delete(id);
////    }
//
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id数组", required = true),
            @ApiImplicitParam(name = "type", value = "状态，0：下架，1：上架", required = true),
    })
    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        return orderService.batchUpdate(batchDTO);
    }

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
    public ResultVO<String> add(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        System.out.println(orderDTO.toString());
        return orderService.insertDynamic(orderDTO);
    }
//
//    /**
//     * 查询 根据主键 id 查询
//     * @author hhj
//     * @date 2021/04/18
//     **/
//    @RequestMapping("/load")
//    public Object load(int id){
//        return orderService.load(id);
//    }
//
//    /**
//     * 查询 分页查询
//     * @author hhj
//     * @date 2021/04/18
//     **/
//    @RequestMapping("/pageList")
//    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
//                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
//        return orderService.pageList(offset, pagesize);
//    }

    @ApiOperation(value = "分类查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO classifyByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return orderService.findPageWithResult(pageDTO);
    }

    @ApiOperation(value = "分类查询")
    @GetMapping("/findById")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<Order> selectById(@Valid Order order, BindingResult bindingResult) {
        System.out.println(order.toString());
        return orderService.selectById(order);
    }

    @ApiOperation(value = "分类查询")
    @GetMapping("/findByIdResult")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Order>> findByIdResult(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return orderService.findByIdResult(pageDTO);
    }

    @ApiOperation(value = "分类统计")
    @GetMapping("/findByIdResultCount")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<OrderTypeDTO> findByIdResultCount(String mid, String uid) {
        return orderService.findByMIdOrUId(mid,uid);
    }

}
