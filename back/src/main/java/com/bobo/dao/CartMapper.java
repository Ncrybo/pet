package com.bobo.dao;

import com.bobo.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    //获取商品数据
    @Select("select * from cart join goods on cart.uid = #{uid} and cart.goods_id = goods.goods_id")
    List<Cart> getCartByUser(Integer uid);

    //创建一个商品记录
    @Insert("insert into cart(uid,goods_id,count) values(#{uid}, #{gid}, 1)")
    public void addCart(Integer uid, Integer gid);

    //购物车商品查重
    @Select("select * from cart where uid = #{uid} and goods_id = #{gid}")
    Cart[] findCartRepeat(Integer uid, Integer gid);

    //商品加一
    @Update("update cart set count = count + 1 where id = #{id}")
    public void addCount(Integer id);

    //商品减一
    @Update("update cart set count = count - 1 where id = #{id}")
    public void downCount(Integer id);

    //删除指定商品
    @Delete("delete from cart where id = #{id}")
    public void delCart(Integer id);

}
