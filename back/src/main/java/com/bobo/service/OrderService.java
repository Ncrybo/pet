package com.bobo.service;

import com.bobo.dao.OrderMapper;
import com.bobo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public List<Order> getOrders(Integer uid){return orderMapper.getOrders(uid);}

    public Integer getLastOrderId(){return orderMapper.getLastOrderId();}

    public void updStatus(Integer status,Integer id){orderMapper.updStatus(status,id);}

    public void addReason(String reason,Integer id){orderMapper.addReason(reason,id);}

    public void detCOrder(Integer id){orderMapper.delOrder(id);}

    public Order getDetail(Integer id){return orderMapper.getDetail(id);}

    public void addOrder(Order order){orderMapper.addOrder(order);}
}
