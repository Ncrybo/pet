package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart  implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer goodsId;
    private String type;
    private Integer count;
    private String goodsName;
    private String shopName;
    private String img;
    private Double price;
    private String describes;
}