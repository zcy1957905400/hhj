package com.hhj.shop.service.impl;

import com.hhj.shop.dto.PageDTO;
import com.hhj.shop.dto.StatisticsDTO;
import com.hhj.shop.dto.WithdrawalDTO;
import com.hhj.shop.repository.AddressRepository;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.repository.OrderRepository;
import com.hhj.shop.repository.WithdrawalRepository;
import com.hhj.shop.service.StatisticsService;
import com.hhj.shop.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    protected static Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Override
    public List<List<String>> findByOrderSales() {
        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
        //String.valueOf(startTs);
        List<StatisticsDTO> OrderSalesDTOList = orderRepository.selectByOrderSales(String.valueOf(startTs - 30 * 24 * 60 * 60), String.valueOf(startTs));
        List<StatisticsDTO> WithdrawalSalesDTOList = withdrawalRepository.selectByWithdrawalSales(String.valueOf(startTs - 30 * 24 * 60 * 60), String.valueOf(startTs));
        log.info(OrderSalesDTOList.toString());
        String date_str = DateUtil.timeStamp2Date(String.valueOf(startTs), "yyyy/MM/dd");
        List<String> date2 = DateUtil.date2OneMonth(date_str, "yyyy/MM/dd");
        List<List<String>> d = new ArrayList<>();
        List<String> saleList = new ArrayList<>();
        List<String> orderList = new ArrayList<>();
        List<String> withdrawalList = new ArrayList<>();
        for (String s : date2) {
            saleList.add("0");
            orderList.add("0");
            withdrawalList.add("0.00");
        }

        for (int i = 0; i < date2.size(); i++) {
            for (StatisticsDTO statisticsDTO : OrderSalesDTOList) {
                if (statisticsDTO.getCreatedate().equals(date2.get(i))) {
                    saleList.set(i, String.valueOf(statisticsDTO.getValue()));
                    orderList.set(i, String.valueOf(statisticsDTO.getTotal()));
                }
            }

            for (StatisticsDTO statisticsDTO : WithdrawalSalesDTOList) {
                if (statisticsDTO.getCreatedate().equals(date2.get(i))) {
                    withdrawalList.set(i, String.valueOf(statisticsDTO.getValue()));
                }
            }

        }
        d.add(date2);
        d.add(orderList);
        d.add(saleList);
        d.add(withdrawalList);
        log.info(saleList.toString() + saleList.size());
        log.info(orderList.toString() + saleList.size());
        log.info(withdrawalList.toString() + withdrawalList.size());
        return d;

    }

    @Override
    public List<String> dataStatistics() {
        Integer memberData=goodsRepository.findPageWithCount(new PageDTO());
        Integer addressData=addressRepository.findPageWithCount(new PageDTO());
        Integer goodsData=goodsRepository.findWithCount();
        BigDecimal orderAmountTotal=orderRepository.findWithAmountTotal();
        Integer OrderTotal=orderRepository.findWithOrderTotal();
        Integer OrderNowTotal=orderRepository.findWithNowOrderTotal();
        List<String> DataStatistics=new ArrayList<>();
        DataStatistics.add(String.valueOf(memberData));
        DataStatistics.add(String.valueOf(addressData));
        DataStatistics.add(String.valueOf(goodsData));
        DataStatistics.add(String.valueOf(orderAmountTotal));
        DataStatistics.add(String.valueOf(OrderNowTotal));
        DataStatistics.add(String.valueOf(OrderTotal));
        return DataStatistics;
    }
}
