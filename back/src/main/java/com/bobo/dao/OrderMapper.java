package com.bobo.dao;

import com.bobo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from shop_order join shop_goods As goods on user_id = #{uid} and goods_id = goods.id")
    List<Order> getOrders(Integer uid);

    @Update("update shop_order set status = #{status} where id = #{id}")
    public void updStatus(Integer status,Integer id);

    @Delete("delete from shop_order where id = #{id}")
    public void delOrder(Integer id);

    @Select("select * from shop_order join shop_goods As goods on shop_order.id = #{id} and goods_id = goods.id join " +
            "shop_useraddress on address_id = shop_useraddress.id")
    Order getDetail(Integer id);

    @Insert("insert into shop_order(order_no,goods_id,status,product_count,total_price,address_id " +
            "user_id,order_time) values(#{},#{},#{},#{},#{},)")
}
