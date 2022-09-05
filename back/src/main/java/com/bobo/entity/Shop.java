package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shop implements Serializable {
    private String shopName;
    private String shopImg;
    private double shopStar;
    private String shopProvince;
    private String shopCity;
    private String shopStreet;
}
