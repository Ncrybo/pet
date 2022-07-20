package com.bobo.service;

import com.bobo.dao.GoodsMapper;
import com.bobo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    public List<Goods> getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

    public Goods[] getAllGoods() {
        return goodsMapper.getAllGoods();
    }
}
