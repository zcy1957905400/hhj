package com.hhj.shop.service;

import com.hhj.shop.dto.StatisticsDTO;

import java.util.List;

public interface StatisticsService {

    List<List<String>> findByOrderSales();

    List<String> dataStatistics();

}
