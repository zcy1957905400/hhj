package com.hhj.shop.controller;

import com.hhj.shop.dto.CommissionDTO;
import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.User;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.CommissionService;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.util.NotResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "登录模块")
@RestController
@RequestMapping("/commission")
public class CommissionController {
    @Autowired
    private CommissionService commissionService;

    @ApiImplicitParam(name = "id",value = "管理员",required = true)
    @ApiOperation(value = "获取佣金记录")
    @GetMapping("/findCommissionByUid")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<CommissionDTO>> fiadByCommission(@Valid User user, BindingResult bindingResult) {
        List<CommissionDTO> commissionDTOList=commissionService.selectByuId(user.getId());
        return new ResultVO<>(ResultCode.SUCCESS,commissionDTOList);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true),
    })
    @ApiOperation(value = "佣金分页查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO commissionByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return commissionService.findPageWithResult(pageDTO);
    }

}

