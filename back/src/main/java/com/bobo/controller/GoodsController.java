package com.bobo.controller;

import com.bobo.dao.GoodsMapper;
import com.bobo.entity.Counts;
import com.bobo.entity.Goods;
import com.bobo.entity.Result;
import com.bobo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getGoodsById(@RequestParam("goodsId") Integer goodsId) {
        return Result.succeed("返回当前商品信息",goodsService.getGoodsById(goodsId));
    }

    @PostMapping("/getCartTop")
    public Result getCartTop() {
        Counts[] counts = goodsService.getCartTop();
        Goods[] goods = new Goods[3];
        goods[0] = goodsService.getGoodsById(counts[0].getGoodsId());
        goods[1] = goodsService.getGoodsById(counts[1].getGoodsId());
        goods[2] = goodsService.getGoodsById(counts[2].getGoodsId());
        return Result.succeed("返回用户购物车里出现次数最多的3个商品",goods);
    }

    @PostMapping("/getOrderTop")
    public Result getOrderTop() {
        Counts[] counts1 = goodsService.getOrderTop();
        Goods[] goods1 = new Goods[4];
        goods1[0] = goodsService.getGoodsById(counts1[0].getGoodsId());
        goods1[1] = goodsService.getGoodsById(counts1[1].getGoodsId());
        goods1[2] = goodsService.getGoodsById(counts1[2].getGoodsId());
        goods1[3] = goodsService.getGoodsById(counts1[3].getGoodsId());
        return Result.succeed("返回用户订单里出现次数最多的4个商品",goods1);
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
