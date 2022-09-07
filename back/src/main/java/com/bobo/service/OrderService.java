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

    public void updStatus(Integer status,Integer id){orderMapper.updStatus(status,id);}

    public void detCOrder(Integer id){orderMapper.delOrder(id);}
}
