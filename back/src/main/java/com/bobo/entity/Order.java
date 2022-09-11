package com.bobo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private String shopName;
    private String goodsName;
    private String img;
    private String describes;
    private String name;
    private String address;
    private String tel;
}
