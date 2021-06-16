package com.hhj.shop.controller;


import com.hhj.shop.util.NotResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexCommonController {

    @GetMapping("/index")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String index(){
        return "index/index";
    }

    @GetMapping("/business")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String business(){
        return "index/business";
    }

    @GetMapping("/shopcart")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String shopcart(){
        return "index/shopcart";
    }

    @GetMapping("/user_details")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_details(){
        return "index/user/details";
    }

    @GetMapping("/user_information")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_information(){
        return "index/user/information";
    }

    @GetMapping("/user_order_info")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_order(){
        return "index/user/order";
    }

    @GetMapping("/details")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String details(){
        return "index/details";
    }

    @GetMapping("/order_info")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String order(){
        return "index/order";
    }

    @GetMapping("/information")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String information(){
        return "index/information";
    }

    @GetMapping("/commodity")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String commodifty(){
        return "index/commodity";
    }

    @GetMapping("/personal")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String personal(){
        return "index/personal";
    }

    @GetMapping("/address")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String address(){
        return "index/address";
    }

    @GetMapping("/apply")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String apply(){
        return "index/apply";
    }

    @GetMapping("/add_add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String add_add(){
        return "index/add_add";
    }

    @GetMapping("/recommend")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String recommend(){
        return "index/recommend";}

    @GetMapping("/footprint")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String footprint(){
        return "index/footprint";}

    @GetMapping("/user_add")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_add(){
        return "index/user/user_add";}


    @GetMapping("/user_login")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_login(){
        return "index/user/login";}

    @GetMapping("/user_forget")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_forget(){
        return "index/user/forget";}

    @GetMapping("/user_personal")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_personal(){
        return "index/user/personal";}

    @GetMapping("/user_commission")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_commission(){
        return "index/user/commission";}

    @GetMapping("/user_wdetailed")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_wdetailed(){
        return "index/user/wdetailed";}
    @GetMapping("/user_withdrawals")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String user_withdrawals(){
        return "index/user/withdrawals";}

    @GetMapping("/register")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String register(){
        return "index/register";}

    @GetMapping("/forget")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String forget(){
        return "index/forget";}

    @GetMapping("/login")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String login(){
        return "index/login";}

    @GetMapping("/search")
    @NotResponseBody  //是否绕过数据统一响应开关
    public String search(){
        return "index/search1";}
}
