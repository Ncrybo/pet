package com.bobo.dao;

import com.bobo.entity.Shop;
import com.bobo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopMapper {
    @Select("select * from shop where shop_name = #{name}")
    public Shop getByName(String name);

    @Select("select * from shop")
    public Shop[] getAllShop();
}
