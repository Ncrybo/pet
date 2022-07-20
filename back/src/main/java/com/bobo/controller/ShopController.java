package com.bobo.controller;

import com.bobo.dao.GoodsMapper;
import com.bobo.dao.ShopMapper;
import com.bobo.entity.Goods;
import com.bobo.entity.Result;
import com.bobo.entity.Shop;
import com.bobo.service.LoginService;
import com.bobo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @PostMapping("/getAllShop")
    public Result getAllShop() {
        Shop[] shops = shopService.getAllShop();
        return Result.succeed("返回所有商店信息",shops);
    }

    @GetMapping("/getShopInfo")
    public Result getShopInfo(@RequestParam("shopName") String shopName) {
        Shop shop = shopMapper.getByName(shopName);
        if(shop != null) {
            return Result.succeed("已查询到信息",shop);
        }
        else {
            return Result.fail("未查询到该商店的信息");
        }
    }
}
