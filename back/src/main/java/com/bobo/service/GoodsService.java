package com.bobo.service;

import com.bobo.dao.GoodsMapper;
import com.bobo.entity.Counts;
import com.bobo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Counts[] getCartTop(){return goodsMapper.getCartTop();};

    public Counts[] getOrderTop(){return goodsMapper.getOrderTop();};
}
