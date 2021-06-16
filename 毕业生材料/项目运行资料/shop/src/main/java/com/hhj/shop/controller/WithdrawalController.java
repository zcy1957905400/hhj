package com.hhj.shop.controller;

import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.dto.LoginUDTO;
import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.ResultDTO;
import com.hhj.shop.entity.User;
import com.hhj.shop.entity.Withdrawal;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.service.LoginService;
import com.hhj.shop.service.WithdrawalService;
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

@Api(tags = "提现模块")
@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @ApiImplicitParams(
            @ApiImplicitParam(name = "withdrawal", value = "提现记录", required = true)
    )
    @ApiOperation(value = "添加提现记录")
    @PostMapping("/add")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> insert(@RequestBody @Valid Withdrawal withdrawal, BindingResult bindingResult) {
        System.out.println(withdrawal.getUId());
        return withdrawalService.insert(withdrawal);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="提现记录id数组",required=true),
            @ApiImplicitParam(name="status",value="状态，0：未审核，1：通过审核,2：未通过审核",required=true),
    })
    @ApiOperation(value = "批量上架/下架")
    @PostMapping("/batchUpdate")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<String> batchUpdate(@RequestBody BatchDTO batchDTO) {
        System.out.println(batchDTO);
        return withdrawalService.batchUpdate(batchDTO);
    }


    @ApiImplicitParams(
            @ApiImplicitParam(name = "withdrawal", value = "提现记录", required = true)
    )
    @ApiOperation(value = "某个管理员的提现记录")
    @GetMapping("/findByUId")
    //@NotResponseBody  //是否绕过数据统一响应开关
    public ResultVO<List<Withdrawal>> findByUId(@Valid Withdrawal withdrawal, BindingResult bindingResult) {
        System.out.println(withdrawal.getUId());
        return withdrawalService.selectByUId(withdrawal);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required=true),
            @ApiImplicitParam(name="limit",value="每页多少条",required=true),
    })
    @ApiOperation(value = "提现记录分页查询")
    @GetMapping("/ByPage")
    @NotResponseBody  //是否绕过数据统一响应开关
    public ResultDTO withdrawalByPage(@Valid PageDTO pageDTO, BindingResult bindingResult) {
        System.out.println(pageDTO.toString());
        return withdrawalService.findPageWithResult(pageDTO);
    }
}

