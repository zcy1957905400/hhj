package com.hhj.shop.service.impl;

import com.hhj.shop.dto.*;
import com.hhj.shop.entity.*;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.*;
import com.hhj.shop.service.OrderService;
import com.hhj.shop.util.OrderIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hhj
 * @description order
 * @date 2021-04-18
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ShopcartRepository shopcartRepository;
    @Autowired
    private OrderGRepository orderGRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;


    @Autowired
    private CommissionRepository commissionRepository;
    protected static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public ResultVO<String> insertDynamic(OrderDTO orderDTO) {
        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
        History history = historyRepository.selectBymId(orderDTO.getMemberId());
        Order order = new Order();
        order.setCreatetime(String.valueOf(startTs));
        order.setMemberId(orderDTO.getMemberId());
        order.setGoodAmountTotal(orderDTO.getGoodAmountTotal());
        order.setAdId(history.getAdId());
        String oid = OrderIdUtil.getGuid();
        order.setOid(oid);
        order.setAId(history.getUId());
        order.setOrderStatus(0);
        orderRepository.insertDynamic(order);
        List<Shopcart> shopcartList = shopcartRepository.findByShopcart(orderDTO.getMemberId(),orderDTO.getIds());
        List<OrderG> orderGList=new ArrayList<>();

        List<Goods> goodsList=new ArrayList<>();
        for (Shopcart shopcart : shopcartList) {
            OrderG orderG=new OrderG();
            orderG.setOId(oid);
            orderG.setNumber(shopcart.getNumber());
            orderG.setGId(shopcart.getGId());
            orderGList.add(orderG);

            Goods goods=new Goods();
            goods.setInventory(shopcart.getNumber());
            goods.setId(shopcart.getGId());
            goodsList.add(goods);
        }
        List<Goods> goodsList1=goodsRepository.selectDataList(goodsList);
        int x=0;
        for (Goods goods:goodsList1) {
            goods.setInventory(goods.getInventory()-goodsList.get(x).getInventory());
            goods.setSales(goods.getSales()+goodsList.get(x).getInventory());
            x++;

        }
        int y=goodsRepository.updateDynamic1(goodsList1);
        orderGRepository.batchInsert(orderGList);
        int i=shopcartRepository.batchDel(orderDTO.getMemberId(),orderDTO.getIds());
        log.info("删除结果："+i);
        return new ResultVO<>(ResultCode.SUCCESS,oid);
    }

    @Override
    public ResultVO<String> updateDynamic(Order order) {
        return null;
    }

    @Override
    public ResultVO<Order> selectById(Order order) {
        Order order1=orderRepository.selectById(order.getOid());
        OrderG orderG = new OrderG();
        orderG.setOId(order.getOid());
        order1.setGoodsDTOList(orderGRepository.findGoodsWithOrder(orderG));
        return new ResultVO<>(ResultCode.SUCCESS,order1);
    }

    @Override
    public Order selectById1(Order order) {
        Order order1=orderRepository.selectById(order.getOid());
        return order1;
    }

    @Override
    public ResultVO<List<Order>> findByIdResult(PageDTO pageDTO) {
        //共多少个类型
        int totalCount = orderRepository.findPageWithCount(pageDTO);
        //计算共多少页
        pageDTO.setPage(0);
        pageDTO.setLimit(totalCount);
        List<Order> orderList=orderRepository.findPageWithResult(pageDTO);
        for (Order order : orderList) {
            OrderG orderG = new OrderG();
            orderG.setOId(order.getOid());
            order.setGoodsDTOList(orderGRepository.findGoodsWithOrder(orderG));
        }
        return new ResultVO<>(ResultCode.SUCCESS,orderList);
    }


    @Override
    public ResultDTO findPageWithResult(PageDTO pageDTO) {
        String keywords = pageDTO.getKeywords();
        if (keywords != null && keywords != "") {
            pageDTO.setKeywords('%' + keywords + '%');
        }
        System.out.println(keywords);
        //共多少个类型
        int totalCount = orderRepository.findPageWithCount(pageDTO);
        //计算共多少页
        int pageSize = pageDTO.getLimit();
        pageDTO.setPage((pageDTO.getPage() - 1) * pageSize);
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        System.out.println(pageDTO);
        List<Order> orderList = orderRepository.findPageWithResult(pageDTO);
        log.info("order-aid:" + orderList.get(0).getOid());
        for (Order order : orderList) {
            OrderG orderG = new OrderG();
            orderG.setOId(order.getOid());
            order.setGoodsDTOList(orderGRepository.findGoodsWithOrder(orderG));
        }
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setData(orderList);
        //ResultPageVO<List<resultDTO>> listResultPageVO=new ResultPageVO<>(resultDTO);
        System.out.println(resultDTO);
        if (orderList.size() != 0) {
            resultDTO.setCode(0);
            log.info("总数：" + totalCount);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("请求成功");
            resultDTO.setData(orderList);
            return resultDTO;
        } else {
            resultDTO.setCode(5000);
            resultDTO.setCount(totalCount);
            resultDTO.setMsg("未知错误");
            return resultDTO;
        }
    }

    @Override
    public ResultVO<String> batchUpdate(BatchDTO batchDTO) {
        log.info("传递的id:" + batchDTO.getOids().get(0));
        List<Order> orderList = new ArrayList<>();
        for (String i : batchDTO.getOids()) {
            Order order = new Order();
            order.setOid(i);
            order.setOrderStatus(batchDTO.getStatus());
            log.info("status:"+batchDTO.getStatus());
            if (batchDTO.getStatus()==2){
                Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
                order.setOrderSettlementTime(String.valueOf(startTs));
            }
            orderList.add(order);
            if (batchDTO.getStatus()==2){
                Commission commission=new Commission();
                String str1="0.50";
                BigDecimal bd=new BigDecimal(str1);
                commission.setAmount(bd);
                commission.setOrderId(i);
                commission.setType(0);
                commissionRepository.insertDynamic(commission);
                User user=userRepository.selectoId(i);
                user.setLeaveAmount(user.getLeaveAmount().add(bd));
                userRepository.updateDynamic(user);
            }

        }
        orderRepository.batchUpdate(orderList);
        return new ResultVO<>(ResultCode.SUCCESS, null);
    }

    @Override
    public ResultVO<OrderTypeDTO> findByMIdOrUId(String mid, String uid) {
        return new ResultVO<>(ResultCode.SUCCESS,orderRepository.findByMIdOrUId(mid, uid));
    }


}