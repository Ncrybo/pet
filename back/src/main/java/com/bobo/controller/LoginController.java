package com.bobo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwk.InvalidPublicKeyException;
import com.bobo.dao.UserMapper;
import com.bobo.entity.Result;
import com.bobo.entity.HuaweiToken;
import com.bobo.entity.User;
import com.bobo.service.LoginService;
import com.bobo.utils.IDTokenParser;
import com.bobo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     *  解析token，获取用户信息
     * Return Result("msg",User)""
     */
    @PostMapping("/parseToken")
    public Result login(@RequestBody HuaweiToken token) {
        System.out.println(token.getId_token());
        IDTokenParser idTokenParser = new IDTokenParser();
        JSONObject idToken;

        /*
           解析id_token
        */
        try {
            idToken = JSONObject.parseObject(idTokenParser.verify(token.getId_token()));
        } catch (InvalidPublicKeyException e) {
            e.printStackTrace();
            return Result.fail("解析出错，访问频繁！"+ e);
        }
        /*
           解析成功，通过名字查找数据库
           如果没有该用户，自动添加
           如果有，则返回用户信息
           sub 值是同一用户不同应用的唯一标识
         */
        User user = new User();
        String thirdId= idToken.getString("sub");
        String userImg = idToken.getString("picture");
        String username = idToken.getString("display_name");
        User myUser = userMapper.getByThirdId(thirdId);

        user.setImg(userImg);
        user.setUname(username);
        user.setThirdId(thirdId);
        user.setToken(JwtUtils.generateToken(user.getUname()));
        if(myUser != null) {
            myUser.setToken(JwtUtils.generateToken(myUser.getUname()));
            return Result.succeed("已查询到用户",myUser);
        }
        else {
            System.out.println("正在新增用户。。。");
            userMapper.insertUser(username,"fdvSRGdsft",userImg,null,thirdId);
        }
        return Result.succeed("欢迎新用户！",user);
    }

    @GetMapping("/verifyToken")
    public Result verifyToken() {
        return Result.succeed("token有效");
    }

    @PostMapping("/addUser/{uname}/{psw}")
    public void addUser(@PathVariable("uname") String uname,@PathVariable("psw")String psw){
        loginService.addUser(uname,psw);
    }
}
