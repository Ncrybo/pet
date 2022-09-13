package com.bobo.controller;

import com.bobo.entity.Order;
import com.bobo.entity.Result;
import com.bobo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @PostMapping("/addReason/{reason}/{id}")
    public Result addReason(@PathVariable("reason")String reason,@PathVariable("id")Integer id){
        orderService.addReason(reason,id);
        return Result.succeed("理由添加成功",1);
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
        Timestamp t = new Timestamp(date.getTime());
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        order.setOrderTime(t);
        Random r = new Random(1);
        order.setOrderNo(str +r.nextInt(90) + 10);
        order.setStatus(1);
        orderService.addOrder(order);
        return Result.succeed("生成订单成功",1);
    }
}
