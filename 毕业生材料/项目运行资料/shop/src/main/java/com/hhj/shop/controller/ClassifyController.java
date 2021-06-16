package com.hhj.shop.controller;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.ClassifyService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "分类模块")
@RestController
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    @ApiImplicitParam(name = "id",value = "分类id",required = true)
    @ApiOperation(value = "根据id删除某个分类")
    @PostMapping("/delete")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<ClassifyDTO> delete(@Valid @RequestBody ClassifyDTO classifyDTO, BindingResult bindingResult) {
        return classifyService.delete(classifyDTO.getId());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="clsName",value="分类名",required=true),
            @ApiImplicitParam(name="synopsis",value="简介",required=true),
            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer")
    })
    @ApiOperation(value = "添加分类")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> add(@Valid @RequestBody Classify classify, BindingResult bindingResult) {

        return classifyService.insert(classify);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="分类id",required=true)
    })
    @ApiOperation(value = "根据id获取分类")
    @GetMapping("/findById")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<Classify> findById(@Valid  ClassifyDTO classifyDTO, BindingResult bindingResult) {
        return classifyService.findById(classifyDTO.getId());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="分类id",required=true),
            @ApiImplicitParam(name="clsName",value="分类名",required=true),
            @ApiImplicitParam(name="synopsis",value="简介",required=true),
            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer"),
            @ApiImplicitParam(name="createtime",value="创建时间",required=true)
    })
    @ApiOperation(value = "添加分类")
    @PostMapping("/revise")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> update(@Valid @RequestBody Classify classify, BindingResult bindingResult) {
        return classifyService.update(classify);
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
        return classifyService.findPageWithResult(pageDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true),
    })
    @ApiOperation(value = "分类查询")
    @GetMapping("/ByPage1")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO classifyByPage1(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        pageDTO.setStatus("1");
        return classifyService.findPageWithResult(pageDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="classifyids",value="分类id数组",required=true),
            @ApiImplicitParam(name="type",value="状态，0：下架，1：上架",required=true),
    })
    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        return classifyService.batchUpdate(batchDTO);
    }

//    @ApiOperation(value = "分类总数")
//    @GetMapping("/findPageWithCount")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<String> findPageWithCount() {
//        return classifyService.findPageWithCount();
//    }

    @ApiOperation(value = "分类id和名称")
    @PostMapping("/findAllClassify")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<ClassifyAllDTO>> findAllClassify() {
        return classifyService.findAllClassify();
    }

    @RequestMapping(value="/ajaxUpload",method={RequestMethod.GET,RequestMethod.POST})
    public ResultVO<String> excel(@RequestParam("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        classifyService.ajaxUploadExcel(file, request, response);
        return new ResultVO<>(ResultCode.SUCCESS,null);
    }
}

