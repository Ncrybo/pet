package com.bobo.service;

import com.bobo.dao.UserMapper;
import com.bobo.entity.Result;
import com.bobo.entity.User;
import com.bobo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;


    public Result login(User user) {            //数据库登录
        User myuser = userMapper.getByName(user.getUname());
        if(myuser.getPsw().equals(user.getPsw())) {
            myuser.setToken(JwtUtils.generateToken(myuser.getUname()));
            return Result.succeed("登录成功！",myuser);
        }
        else {
            return Result.fail("错误");
        }
    }

    public void addUser(String uname,String psw){
        userMapper.addUser(uname, psw);
    }
}
