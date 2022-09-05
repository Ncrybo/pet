package com.bobo.dao;

import com.bobo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //get userInfo by user's name
    @Select("select * from shop_user where uname = #{name}")
    public User getByName(String name);

    //新增用户
    @Insert("INSERT INTO shop_user(uname,psw,img,phone,third_id) values(#{uname}, #{psw}, #{img},#{phone}, #{third_id})")
    public Integer insertUser(@Param("uname") String uname, @Param("psw")String psw, @Param("img")String img,
                              @Param("phone") String phone, @Param("third_id")String thridId);


    //get userInfo by thirdId
    @Select("select * from shop_user where third_id = #{third_id}")
    public User getByThirdId(String thirdId);

    //数据库普通添加用户
    @Insert("insert into shop_user(uname,psw,phone,third_id) values(#{uname}, #{psw},null,null)")
    public Integer addUser(String uname,String psw);
}
