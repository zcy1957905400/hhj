package com.hhj.shop.controller;

import com.hhj.shop.util.NotResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCommonController {

    @GetMapping("/index")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String index(){
        return "admin/index";
    }

    @GetMapping("/login")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String login(){
        return "admin/login2";
    }

    @GetMapping("/welcome")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String welcome(){
        return "admin/html/welcome";
    }

    @GetMapping("/withdrawal_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String withdrawal_list(){
        return "admin/html/withdrawal/withdrawal-list";
    }

    @GetMapping("/commission_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String commission_list(){
        return "admin/html/commission/commission-list";
    }

    @GetMapping("/classify_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String classify_list(){
        return "admin/html/classify/classify-list";
    }
    @GetMapping("/classify_batch")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String classify_batch(){
        return "admin/html/classify/classify-batch";
    }
    @GetMapping("/classify_add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String classify_add(){
        return "admin/html/classify/classify-add";
    }

    @GetMapping("/goods_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String goods_list(){
        return "admin/html/goods/goods-list";
    }
    @GetMapping("/goods_batch")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String goods_batch(){
        return "admin/html/goods/goods-batch";
    }
    @GetMapping("/goods_add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String goods_add(){
        return "admin/html/goods/goods-add";
    }
    @GetMapping("/goods_edit")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String goods_edit(){
        return "admin/html/goods/goods-edit";
    }

    @GetMapping("/order_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String order_list(){
        return "admin/html/order/order-list";
    }

    @GetMapping("/member_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String member_list(){
        return "admin/html/member/member-list";
    }

//    @GetMapping("/order_batch")
//    @NotResponseBody  //是否绕过数据统一响应开关
//    public String order_batch(){
//        return "admin/html/goods/goods-batch";
//    }
//    @GetMapping("/order_add")
//    @NotResponseBody  //是否绕过数据统一响应开关
//    public String order_add(){
//        return "admin/html/goods/order-add";
//    }

    @GetMapping("/user_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_list(){
        return "admin/html/user/user-list";
    }

    @GetMapping("/user_add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String order_add(){
        return "admin/html/user/user-add";
    }
    @GetMapping("/user_edit")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_edit(){
        return "admin/html/user/user-edit";
    }

    @GetMapping("/user_editpwd")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_editpwd(){
        return "admin/html/user/user_editpwd";
    }
    @GetMapping("/address_list")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String address_list(){
        return "admin/html/address/address-list";
    }
    @GetMapping("/echarts_china")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String echarts_china(){
        return "admin/html/echarts9";
    }

    @GetMapping("/echarts_LineChart")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String echarts_LineChart(){
        return "admin/html/echarts1";
    }
}
