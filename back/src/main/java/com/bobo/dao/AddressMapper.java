package com.bobo.dao;

import com.bobo.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("select * from useraddress where uid = #{uid}")
    List<Address> getAddress(Integer uid);

    @Select("select * from useraddress where id = #{id}")
    Address getAddressById(Integer id);

    @Insert("insert into useraddress(uid,name,address,tel) values(#{uid},#{name},#{address},#{tel})")
    void addAddress(Integer uid,String name,String address,String tel);

    @Update({"update useraddress set name=#{name},address=#{address},tel=#{tel} where id=#{id}"})
    void editAddress(String name,String address,String tel,Integer id);

    @Delete("delete from useraddress where id = #{id}")
    void detAddress(Integer id);
}
