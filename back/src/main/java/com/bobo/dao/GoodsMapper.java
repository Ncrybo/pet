package com.bobo.dao;

import com.bobo.entity.Counts;
import com.bobo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface GoodsMapper {
    @Select("select * from shop_goods where id = #{goodsId}")
    public Goods getGoodsById(Integer goodsId);

    @Select("select * from shop_goods")
    public Goods[] getAllGoods();

    //查询用户购物车里出现次数最多的3个商品
    @Select("select goods_id,count(1) from shop_cart group by goods_id order by count(1) desc limit 3")
    public Counts[] getCartTop();

    //查询用户订单里出现次数最多的4个商品
    @Select("select goods_id,count(1) from shop_order group by goods_id order by count(1) desc limit 4")
    public Counts[] getOrderTop();

    //查询某个商店的所有商品
    @Select("select * from shop_goods where shop_name = #{shopName}")
    public Goods[] getGoodsByShopName(String shopName);

    //查询某个商店的最低价的3个pet
    @Select("select * from shop_goods where shop_name = #{淘宠} and type = #{pet} order by price limit 3")
    public Goods[] getTreePet(String shopName);

    //赛选某个商家的某个种类的所有商品
    @Select("select * from shop_goods where shop_name = #{shopName} and type = #{type}")
    public Goods[] checkGoods(String shopName, String type);
}
