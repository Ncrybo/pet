package com.bobo.service;

import com.bobo.dao.ShopMapper;
import com.bobo.dao.UserMapper;
import com.bobo.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ShopService {
    @Autowired
    ShopMapper shopMapper;

    public Shop getShopByname(String name) {
        return shopMapper.getByName(name);
    }

    public Shop[] getAllShop() {
        return shopMapper.getAllShop();
    }

}
