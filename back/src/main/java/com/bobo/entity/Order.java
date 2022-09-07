package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private Integer id;
    private String orderNo;
    private Integer goodsId;
    private Integer status;
    private Integer productCount;
    private Double totalPrice;
    private Integer addressId;
    private Integer userId;
    private Date orderTime;
    private String shopName;
    private String img;
    private String describes;
}
