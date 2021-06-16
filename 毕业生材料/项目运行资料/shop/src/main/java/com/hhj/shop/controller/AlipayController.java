package com.hhj.shop.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hhj.shop.config.AlipayConfig;
import com.hhj.shop.dto.BatchDTO;
import com.hhj.shop.entity.Order;
import com.hhj.shop.service.OrderService;
import com.hhj.shop.util.NotResponseBody;
import com.hhj.shop.util.QR;
//import com.suke.czx.common.annotation.AuthIgnore;
//import com.suke.czx.modules.oo.entity.OoOrder;
//import com.suke.czx.modules.oo.service.OoOrderService;
//import com.suke.czx.pay.QRcode.QR;
//import com.suke.czx.pay.alipay.AlipayConfig;
//import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private OrderService orderService;
    protected static Logger log = LoggerFactory.getLogger(AlipayController.class);

    /**
     * 阿里订单取消接口
     * 请求返回值示例:
     * {"alipay_trade_cancel_response":{"code":"40004","msg":"Business Failed","sub_code":"ACQ.INVALID_PARAMETER","sub_msg":"参数无效","out_trade_no":"20150320010101002",
     * "retry_flag":"N"},"sign":""}
     * @return
     * @throws AlipayApiException
     */
//    @RequestMapping("/aliremove.do")
//    @ResponseBody
//    @AuthIgnore
//    private Map<String,Object>  remove(@Param("orderid") String orderid) throws AlipayApiException {
//        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id,
//                AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.zfb_public_key,"RSA2");//获得初始化的AlipayClient
//        //创建API对应的request类
//        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
//        Map<String, Object> returnmap = new HashMap<>();
//
//       try {
//           //判断订单是否完成支付，完成支付的订单禁止撤销
//           QueryWrapper<OoOrder> queryWrapper = new QueryWrapper<>();
//           queryWrapper.eq("ooid", orderid);
//           List<OoOrder> i = this.ooOrderService.list(queryWrapper);
//           if (i.size() != 0) {
//               returnmap.put("type", "0");
//               returnmap.put("data", "订单已支付，撤销订单失败");
//           } else {
//               Map<String, Object> map = new HashMap<>();
//               map.put("out_trade_no", orderid);
//               String returndata = JSONObject.fromObject(map).toString();
//               request.setBizContent(returndata);//设置业务参数
//               AlipayTradeCancelResponse response = alipayClient.execute(request);//通过alipayClient调用API，获取对应的response类
//               JSONObject query_response = JSONObject.fromObject(response.getBody()).getJSONObject("alipay_trade_cancel_response");
//               String code = query_response.getString("code");
//               String msg = query_response.getString("msg");
//               if (code.equals("10000") && msg.equals("Success")) {
//                   System.out.println("阿里订单取消接口调用成功！");
//                   returnmap.put("type", "1");
//                   returnmap.put("data", "撤销订单成功");
//               } else {
//                   System.out.println("撤销订单请求失败");
//                   returnmap.put("type", "0");
//                   returnmap.put("data", query_response.getString("sub_msg"));
//                   System.out.println(query_response);//返回失败信息
//               }
//               //根据response中的结果继续业务逻辑处理
//
//           }
//       }catch (Exception e){
//           System.out.println("撤销订单异常");
//           e.printStackTrace();
//       }
//        return returnmap;
//    }

    /**
     * 阿里交易查询接口
     * out_trade_no  支付时传入的商户订单号，与trade_no必填一个
     * trade_no  支付时返回的支付宝交易号，与out_trade_no必填一个
     * 请求返回值示例：
     * {"alipay_trade_query_response":{"code":"10000","msg":"Success","buyer_logon_id":"159****4027","buyer_pay_amount":"0.01","buyer_user_id":"2088012351746164",
     * "fund_bill_list":[{"amount":"0.01","fund_channel":"PCREDIT"}],"invoice_amount":"0.01","out_trade_no":"020910312752381","point_amount":"0.00","receipt_amount":"0.01",
     * "send_pay_date":"2018-02-09 10:31:45","total_amount":"0.01","trade_no":"2018020921001004160275738069","trade_status":"TRADE_SUCCESS"},
     * "sign":""}
     *
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/aliget")
//    @ResponseBody
//    @AuthIgnore
//    private Map<String, Object> get(@Param("orderid") String orderid) throws AlipayApiException {
//        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id,
//                AlipayConfig.private_key, "json", AlipayConfig.input_charset, AlipayConfig.zfb_public_key, "RSA2");//获得初始化的AlipayClient
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        Map<String, Object> map = new HashMap<>();
//        map.put("out_trade_no", orderid);
//        String returndata = JSONObject.fromObject(map).toString();
//        request.setBizContent(returndata);//设置业务参数
//        AlipayTradeQueryResponse response = alipayClient.execute(request);
//
//        JSONObject jsonObject = JSONObject.fromObject(response.getBody()).getJSONObject("alipay_trade_query_response");
//        String code = jsonObject.getString("code");
//        String msg = jsonObject.getString("msg");
//        Map<String, Object> returnmap = new HashMap<>();
//        if (code.equals("10000") && msg.equals("Success")) {
//            System.out.println("交易查询请求成功");
//            String trade_status = jsonObject.getString("trade_status");
//            if (trade_status.equals("TRADE_SUCCESS")) {
//                returnmap.put("type", "1");
//                returnmap.put("data", "支付成功");
//            } else {
//                returnmap.put("type", "0");
//                returnmap.put("data", trade_status);
//            }
//        } else {
//            System.out.println("交易查询请求失败");
//            returnmap.put("type", "0");
//            returnmap.put("data", jsonObject.getString("sub_msg"));
//            System.out.println(jsonObject);//返回失败信息
//        }
//        return returnmap;
//    }

    /**
     * @name 预下单请求，阿里获取二维码接口
     * * @throws AlipayApiException
     * * @Param out_trade_no 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
     * * @Param total_amount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，【不可打折金额】，
     * * 【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】
     * * @Param subject 订单标题
     * * @Param store_id 商户门店编号
     * * @Param timeout_express 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    @RequestMapping("/pay")
    @ResponseBody
    private Map list(HttpServletResponse responses, @Param("orderid") String orderid) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ServiceUrl, AlipayConfig.app_id, AlipayConfig.private_key,
                "json", AlipayConfig.input_charset, AlipayConfig.zfb_public_key, "RSA2"); //获得初始化的AlipayClient
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        Map<String, Object> map = new HashMap<>();
        if (orderid.isEmpty()) {
            map.put("type", "2");
            map.put("data", "订单号不能为空");
            return map;
        }
        //设置回调地址
        request.setNotifyUrl("http://hhj.nat300.top/shop/alipay/aliPayCallBack");
        request.setReturnUrl("http://hhj.nat300.top/shop/alipay/aliPayCallBack");
        //根据订单号查询订单信息
        Order order = new Order();
        order.setOid(orderid);
        order = orderService.selectById1(order);
//        QueryWrapper<OoOrder> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("ooid", orderid);
//        List<OoOrder> list = this.ooOrderService.list(queryWrapper);
        Map<String, Object> maps = new HashMap<>();
        maps.put("out_trade_no", orderid);
        maps.put("total_amount", order.getGoodAmountTotal());
        maps.put("subject", "订单" + orderid);
        maps.put("store_id", "XB001");
        maps.put("timeout_express", "1m");
        //把订单信息转换为json对象的字符串
        JSONObject jsonObject1 = new JSONObject(maps);
        String postdata = jsonObject1.toString();
//        String postdata = JSONObject.fromObject(maps).toString();
        request.setBizContent(postdata);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        String body = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);

        //JSONObject jsonObject = JSONObject.fromObject(body);
        String qr_code = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
        System.out.println(qr_code);
        //输出流
        ServletOutputStream sos = null;
        try {
            sos = responses.getOutputStream();
            QR qr = new QR();
            qr.makeQRCode2(qr_code, sos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //log.info(Map);
        return map;
    }

    /**
     * 支付宝支付成功回调
     */
    @RequestMapping("/aliPayCallBack")
    //@ResponseBody
    @NotResponseBody  //是否绕过数据统一响应开关
    public String aliPayCallBack(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("支付宝回调成功");
        try {
            //获取支付宝POSt过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                //valueStr=new String(valuseStr.getBytes("ISO-8859-1"),"utf-8));
                params.put(name, valueStr);
            }
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.zfb_public_key, AlipayConfig.input_charset, "RSA2");
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
            String order_nio = request.getParameter("out_trade_no");//获取订单号
            String trade_no = request.getParameter("trade_no"); // 支付宝交易号
//			String total_amount = request.getParameter("total_amount"); // 获取总金额
//			String subject = new String(request.getParameter("subject")
//					.getBytes("ISO-8859-1"), "gbk");// 商品名称、订单名称
//			String body = "";
//			if (request.getParameter("body") != null) {
//				body = new String(request.getParameter("body").getBytes(
//						"ISO-8859-1"), "gbk");// 商品描述、订单备注、描述
//			}
//			String buyer_email = request.getParameter("buyer_email"); // 买家支付宝账号

            String trade_status = request.getParameter("trade_status");//交易状态
            //获取支付宝的通知返回参数，，可参考技术文档中页面跳转同步通知参数列表
            if (flag == true) {
                try {
                    BatchDTO batchDTO = new BatchDTO();
                    List<String> i = new ArrayList();
                    i.add(order_nio);
                    batchDTO.setOids(i);
                    batchDTO.setStatus(1);
                    this.orderService.batchUpdate(batchDTO);
                    //response.sendRedirect(request.getContextPath()+"/index/order.html");
                    return "redirect:/index/order_info";

                } catch (Exception e) {
                    System.out.println("支付宝支付异常");
                    e.printStackTrace();
                }
            }


        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

}
