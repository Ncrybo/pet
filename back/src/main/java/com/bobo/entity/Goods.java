package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {
    private Integer id;
    private String goodsName;
    private String shopName;
    private String img;
    private Double price;
    private String describes;
    private Integer type;
}
