package com.bobo.dao;

import com.bobo.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from shop_order join shop_goods As goods on user_id = #{uid} and goods_id = goods.id")
    List<Order> getOrders(Integer uid);

    @Update("update shop_order set status = #{status} where id = #{id}")
    public void updStatus(Integer status,Integer id);

    @Delete("delete from shop_order where id = #{id}")
    public void delOrder(Integer id);
}
