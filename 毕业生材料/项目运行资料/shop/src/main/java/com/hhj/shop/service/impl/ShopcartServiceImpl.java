package com.hhj.shop.service.impl;

import com.hhj.shop.dto.ShopcartDTO;
import com.hhj.shop.entity.Goods;
import com.hhj.shop.entity.Shopcart;
import com.hhj.shop.global.ResultCode;
import com.hhj.shop.global.ResultVO;
import com.hhj.shop.repository.AddressRepository;
import com.hhj.shop.repository.GoodsRepository;
import com.hhj.shop.repository.ShopcartRepository;
import com.hhj.shop.service.GoodsService;
import com.hhj.shop.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartServiceImpl implements ShopcartService {

    @Autowired
    private ShopcartRepository shopcartRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public ResultVO<String> insert(Shopcart shopcart) {
        Shopcart shopcart1 = shopcartRepository.selectByShopcart(shopcart);
        if (shopcart1 == null) {
            shopcart.setNumber(1);
            int result = shopcartRepository.insert(shopcart);
            if (result != 0) {
                return new ResultVO<>(ResultCode.SUCCESS, String.valueOf(result));
            } else {
                return new ResultVO<>(ResultCode.ERROR, null);
            }
        } else {
            shopcart.setId(shopcart1.getId());
            if (shopcart.getStatus() == 1) {
                int result = shopcartRepository.delete(shopcart.getId());
                if (result != 0) {
                    return new ResultVO<>(ResultCode.SUCCESS, String.valueOf(result));
                } else {
                    return new ResultVO<>(ResultCode.ERROR, null);
                }
            } else if (shopcart.getStatus() == 2) {
                shopcart.setNumber(shopcart1.getNumber() - 1);
                int result = shopcartRepository.updateDynamic(shopcart);
                if (result != 0) {
                    return new ResultVO<>(ResultCode.SUCCESS, String.valueOf(result));
                } else {
                    return new ResultVO<>(ResultCode.ERROR, null);
                }
            }else{
                shopcart.setNumber(shopcart1.getNumber()+1);
                int result = shopcartRepository.updateDynamic(shopcart);
                if (result != 0) {
                    return new ResultVO<>(ResultCode.SUCCESS, String.valueOf(result));
                } else {
                    return new ResultVO<>(ResultCode.ERROR, null);
                }
            }
        }
    }

    @Override
    public int insertDynamic(Shopcart shopcart) {
        return 0;
    }

    @Override
    public int updateDynamic(Shopcart shopcart) {
        return 0;
    }

    @Override
    public int update(Shopcart shopcart) {
        return 0;
    }

    @Override
    public Shopcart selectById(Integer id) {
        return null;
    }

    @Override
    public List<Shopcart> findPageWithResult(Shopcart shopcart) {
        return null;
    }

    @Override
    public ResultVO<List<Goods>> findWithResult(ShopcartDTO shopcartDTO) {
        return new ResultVO<>(ResultCode.SUCCESS,goodsRepository.findWithResult(shopcartDTO));
    }

    @Override
    public Integer findPageWithCount(Shopcart shopcart) {
        return null;
    }
}
