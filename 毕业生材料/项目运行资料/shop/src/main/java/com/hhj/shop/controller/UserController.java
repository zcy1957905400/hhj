package com.hhj.shop.controller;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.Classify;
import com.hhj.shop.entity.Member;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.TokenService;
import com.hhj.shop.service.UserService;
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

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    protected static Logger log = LoggerFactory.getLogger(UserController.class);

//    @ApiImplicitParam(name = "id",value = "分类id",required = true)
//    @ApiOperation(value = "根据id删除某个分类")
//    @PostMapping("/delete")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<ClassifyDTO> delete(@Valid @RequestBody ClassifyDTO classifyDTO, BindingResult bindingResult) {
//        return classifyService.delete(classifyDTO.getId());
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="clsName",value="分类名",required=true),
//            @ApiImplicitParam(name="synopsis",value="简介",required=true),
//            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer")
//    })
    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> add(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("管理员信息："+userDTO.toString());
        return userService.insert(userDTO);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/updateUserAddresss")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> updateUserAddresss(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("管理员信息："+userDTO.toString());
        return userService.updateUserAddresss(userDTO);
    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="id",value="分类id",required=true)
//    })
//    @ApiOperation(value = "根据id获取分类")
//    @GetMapping("/findById")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<Classify> findById(@Valid  ClassifyDTO classifyDTO, BindingResult bindingResult) {
//        return classifyService.findById(classifyDTO.getId());
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="id",value="分类id",required=true),
//            @ApiImplicitParam(name="clsName",value="分类名",required=true),
//            @ApiImplicitParam(name="synopsis",value="简介",required=true),
//            @ApiImplicitParam(name="type",value="状态",required=true,dataType="Integer"),
//            @ApiImplicitParam(name="createtime",value="创建时间",required=true)
//    })
//    @ApiOperation(value = "添加分类")
//    @PostMapping("/revise")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<String> update(@Valid @RequestBody Classify classify, BindingResult bindingResult) {
//        return classifyService.update(classify);
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true),
    })
    @ApiOperation(value = "管理员查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO UserByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        log.info("管理员信息分页"+pageDTO.toString());
        return userService.findPageWithResult(pageDTO);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name="classifyids",value="分类id数组",required=true),
//            @ApiImplicitParam(name="type",value="状态，0：下架，1：上架",required=true),
//    })
    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        log.info("status:"+batchDTO.getStatus());
        return userService.batchUpdate(batchDTO);
    }

    @ApiImplicitParam(name="id",value="管理员id",required=true)
    @ApiOperation(value = "获取某个管理员id的信息")
    @GetMapping("/findById")
    public ResultVO<User> findById(@Valid User user){
        User user1=userService.findById(user.getId());
        return new ResultVO<>(ResultCode.SUCCESS,user1);
    }

    @ApiImplicitParam(name="id",value="管理员id",required=true)
    @ApiOperation(value = "获取某个管理员id的信息")
    @PostMapping("/findByUser")
    public ResultVO<UserDTO> findByUser(@Valid @RequestBody User user){
        log.info("uid:"+user.getId());
        UserDTO userDTO=userService.findByUser(user.getId());
        return new ResultVO<>(ResultCode.SUCCESS,userDTO);
    }


    @ApiOperation(value = "修改管理员信息")
    @PostMapping("/update")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        log.info("管理员信息：" + user.toString());
        Object tk = tokenService.gettoken(user.getPhone());
        log.info(tk.toString());
        if (tk.equals( user.getSmsCode())) {
            return userService.update(user);
        } else {
            return new ResultVO<>(ResultCode.CODE_FAIL, null);
        }
    }
//    @ApiOperation(value = "分类总数")
//    @GetMapping("/findPageWithCount")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<String> findPageWithCount() {
//        return classifyService.findPageWithCount();
//    }

//    @ApiOperation(value = "分类id和名称")
//    @PostMapping("/findAllClassify")
//    //@NotResponseBody  //是否绕过数据统一响应开关
//    public ResultVO<List<ClassifyAllDTO>> findAllClassify() {
//        return classifyService.findAllClassify();
//    }
//
//    @RequestMapping(value="/ajaxUpload",method={RequestMethod.GET,RequestMethod.POST})
//    public ResultVO<String> excel(@RequestParam("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Origin","*");
//        classifyService.ajaxUploadExcel(file, request, response);
//        return new ResultVO<>(ResultCode.SUCCESS,null);
//    }
}

