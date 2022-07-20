package com.bobo.controller;

import com.bobo.dao.GoodsMapper;
import com.bobo.entity.Goods;
import com.bobo.entity.Result;
import com.bobo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;

    @PostMapping("/getAllGoods")
    public Result getAllGoods() {
        Goods[] goods = goodsService.getAllGoods();
        return Result.succeed("返回所有商品信息",goods);
    }

    @PostMapping("/getGoodsById")
    public List<Goods> getGoodsById(Integer id) {
        return goodsService.getGoodsById(id);
    }

    @GetMapping("/getTreePet")
    public Result getTreePet(@RequestParam("shopName") String shopName) {
        Goods goods[] = goodsMapper.getTreePet(shopName);
        if (goods != null) {
            return Result.succeed("获取前三个pet信息", goods);
        }
        else {
            return Result.fail("获取前三个信息失败");
        }
    }

    @GetMapping("/getShopGoods")
    public Result getshopGoods(@RequestParam("ShopName") String shopName) {
        Goods shopGoods[] = goodsMapper.getGoodsByShopName(shopName);
        if(shopGoods != null) {
            return Result.succeed("成功获取商店的所有商品",shopGoods);
        }
        else {
            return Result.succeed("没有查询到商品",shopGoods);
        }
    }


    //查询某商店的某个种类的所有商品
    @GetMapping("/checkGoods")
    public Result checkGoods(@RequestParam("type") String type ,@RequestParam("shopName") String shopName) {
        Goods[] goods = goodsMapper.checkGoods(shopName,type);
        if(goods != null){
            return Result.succeed("返回"+shopName+"的"+type+"类型的素有商品",goods);
        }
        else {
            return Result.fail("没有查询到商品");
        }
    }
}
