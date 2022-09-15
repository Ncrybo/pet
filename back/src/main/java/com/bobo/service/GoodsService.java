package com.bobo.service;

import com.bobo.dao.GoodsMapper;
import com.bobo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    public Goods getGoodsById(Integer goodsId) {
        return goodsMapper.getGoodsById(goodsId);
    }

    public Goods[] getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    public Integer[] getCartTop(){return goodsMapper.getCartTop();};

    public Integer[] getOrderTop(){return goodsMapper.getOrderTop();};
}
