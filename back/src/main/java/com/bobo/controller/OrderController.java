package com.bobo.controller;

import com.bobo.entity.Order;
import com.bobo.entity.Result;
import com.bobo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Order order){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));
        System.out.println(date);
        order.setOrderTime(date);
        order.setOrderNo(dateFormat.format(date)+order.getId());
        return Result.succeed("获取订单详情成功",order);
    }
}
