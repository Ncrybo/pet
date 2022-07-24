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

    @Select("select * from useraddress where isDefault = 1 and uid = #{uid}")
    Address getUserDefault(Integer uid);

    @Update({"update useraddress set isDefault = 0 where id=#{id}"})
    void updUserDefault(Integer id);

    @Insert("insert into useraddress(uid,name,address,tel,isDefault) values(#{uid},#{name},#{address},#{tel},#{isDefault})")
    void addAddress(Address address);

    @Update({"update useraddress set name=#{name},address=#{address},tel=#{tel},isDefault=#{isDefault} where id=#{id}"})
    void editAddress(Address address);

    @Delete("delete from useraddress where id = #{id}")
    void detAddress(Integer id);
}
