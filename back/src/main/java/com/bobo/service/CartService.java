package com.bobo.service;

import com.bobo.dao.CartMapper;
import com.bobo.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartMapper cartMapper;

    public List<Cart> getCartByUser(Integer uid){return cartMapper.getCartByUser(uid);}

    public Cart[] findCartRepeat(Integer uid,Integer gid){return cartMapper.findCartRepeat(uid,gid);}

    public void addCart(Integer uid,Integer gid){cartMapper.addCart(uid,gid);}

    public void detCart(Integer id){cartMapper.delCart(id);}

    public void addCount(Integer id){cartMapper.addCount(id);}

    public void downCount(Integer id){cartMapper.downCount(id);}

    public Cart payCart(Integer id){return cartMapper.payCart(id);}
}
