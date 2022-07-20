package com.bobo.dao;

import com.bobo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods where goods_id = #{id}")
    public List<Goods> getGoodsById(Integer id);

    @Select("select * from goods")
    public Goods[] getAllGoods();

    //查询某个商店的所有商品
    @Select("select * from goods where shop_name = #{shopName}")
    public Goods[] getGoodsByShopName(String shopName);

    //查询某个商店的最低价的3个pet
    @Select("select * from goods where shop_name = #{淘宠} and type = #{pet} order by price limit 3")
    public Goods[] getTreePet(String shopName);

    //赛选某个商家的某个种类的所有商品
    @Select("select * from goods where shop_name = #{shopName} and type = #{type}")
    public Goods[] checkGoods(String shopName, String type);
}
