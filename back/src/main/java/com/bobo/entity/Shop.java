package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shop implements Serializable {
    private String shopName;
    private String img;
    private double star;
    private String province;
    private String city;
    private String street;
}
