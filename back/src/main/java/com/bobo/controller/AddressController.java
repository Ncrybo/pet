package com.bobo.controller;

import com.bobo.entity.Address;
import com.bobo.entity.Result;
import com.bobo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/getAddress/{uid}")
    public Result getAddress(@PathVariable("uid")Integer uid){
        List<Address> addressList = addressService.getAddress(uid);
        return Result.succeed("获取地址成功",addressList);
    }

    @PostMapping("/getAddressById")
    public Result getAddressById(@RequestParam("id") Integer id){
        return Result.succeed("获取当前地址成功",addressService.getAddressById(id));
    }

    @PostMapping("/addAddress/{uid}/{name}/{address}/{tel}")
    public Result addAddress(@PathVariable("uid")Integer uid,@PathVariable("name")String name,@PathVariable("address")String address,@PathVariable("tel")String tel){
        addressService.addAddress(uid,name,address,tel);
        return Result.succeed("添加成功",1);
    }

    @PostMapping("/editAddress")
    public Result editAddress(@RequestParam("name")String name,@RequestParam("address")String address,@RequestParam("tel")String tel,@RequestParam("id")Integer id){
        addressService.editAddress(name,address,tel,id);
        return Result.succeed("修改成功",1);
    }

    @PostMapping("/detAddress")
    public Result detAddress(@RequestParam("id") Integer id){
        addressService.detAddress(id);
        return Result.succeed("删除成功",1);
    }
}
