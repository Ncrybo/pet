package com.bobo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HuaweiToken implements Serializable {
    private String access_token;
    private String expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}

