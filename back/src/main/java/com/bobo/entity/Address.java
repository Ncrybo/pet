package com.bobo.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer uid;
    private String name;
    private String address;
    private String tel;
    private Boolean isDefault;
}
