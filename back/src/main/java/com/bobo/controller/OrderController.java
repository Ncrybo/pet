package com.bobo.controller;

import com.bobo.entity.Order;
import com.bobo.entity.Result;
import com.bobo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/getOrders/{uid}")
    public Result getOrders(@PathVariable("uid")Integer uid){
        List<Order> orderList = orderService.getOrders(uid);
        return Result.succeed("获取订单成功",orderList);
    }

    @PostMapping("/updStatus/{status}/{id}")
    public Result updStatus(@PathVariable("status")Integer status,@PathVariable("id")Integer id){
        orderService.updStatus(status,id);
        return Result.succeed("修改成功",1);
    }

    @PostMapping("/detOrder/{id}")
    public Result detOrder(@PathVariable("id") Integer id){
        orderService.detCOrder(id);
        return Result.succeed("删除成功",1);
    }

    @PostMapping("/getDetail")
    public Result getDetail(@RequestParam("id")Integer id){
        Order order = orderService.getDetail(id);
        return Result.succeed("获取订单详情成功",order);
    }
}
