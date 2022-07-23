package com.bobo.controller;

import com.bobo.entity.Cart;
import com.bobo.entity.Result;
import com.bobo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/getCart/{uid}")
    public Result getCartByUser(@PathVariable("uid") Integer uid){
        List<Cart> cartList =  cartService.getCartByUser(uid);//这是从数据库获取的购物车商品列表
        Map<String,List<Cart>> map = new HashMap<>();//新建一个map,准备用来分组
        for (Cart shoppingCart : cartList) {
            //将结果以店铺名字进行分组
            List<Cart> groupList = map.get(shoppingCart.getShopName());
            if (groupList == null) {
                groupList = new ArrayList<Cart>();//如果没有获取到,新建组,组名是店铺名字
                groupList.add(shoppingCart);
                map.put(shoppingCart.getShopName(), groupList);
            }else{
                groupList.add(shoppingCart);
            }
        }//写到这分组就好了,map中key就是组名,值就是对应的商品列表;但前端还要求此组名key用同一个字段,所以还需要处理
//思路是这样的,再建一个Map,命名为resMap,把上面map中的商品列表数据取出来,再放到新的resMap中,并把新的key命名为"list",然后把这个resMap添加到一个resList集合中;以下是具体步骤
        Set<String> keys = map.keySet();//把map中的key拿出来,准备依据key取值
        List<Map<String,Object>> resList = new ArrayList<>();
        for (String key : keys) {
            List<Cart> list = map.get(key);//使用循环,通过Key把map中的数据依次取出来
            Map<String,Object> resMap = new HashMap<>();//每次循环都创建新的resMap
            resMap.put("list", list);//取出来后重新命名添加到新的resMap中
            resMap.put("shopName", list.get(0).getShopName());//同时把店铺名字单独拿出来添加进去
            resList.add(resMap);//把处理好的数据添加到reslist
        }
        return Result.succeed("返回所有商品信息",resList);
    }

    @PostMapping("/addCart/{uid}/{gid}")
    public Result addCart(@PathVariable("uid") Integer uid,@PathVariable("gid") Integer gid){
        Cart[] cartList = cartService.findCartRepeat(uid,gid);
        if(cartList.length == 0) {
            cartService.addCart(uid, gid);
            return Result.succeed("添加成功",1);
        }
        else
            return Result.succeed("商品已存在，不添加",0);
    }

    @PostMapping("/detCart/{id}")
    public Result detCart(@PathVariable("id") Integer id){
        cartService.detCart(id);
        return Result.succeed("删除成功",1);
    }

    @PostMapping("/addCount/{id}")
    public Result addCount(@PathVariable("id") Integer id){
        cartService.addCount(id);
        return Result.succeed("商品+1成功",1);
    }

    @PostMapping("/downCount/{id}")
    public Result downCount(@PathVariable("id") Integer id){
        cartService.downCount(id);
        return Result.succeed("商品-1成功",1);
    }

}
