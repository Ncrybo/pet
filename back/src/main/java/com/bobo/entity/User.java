package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String uname;
    private String psw;
    private String thirdId;     //第三方账号id
    private String img;      //头像图片
    private String phone;
    private String token;

}
