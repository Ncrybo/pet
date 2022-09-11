package com.bobo.controller;

import com.bobo.entity.Address;
import com.bobo.entity.Result;
import com.bobo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getAddressDefault/{uid}")
    public Result getAddressDefault(@PathVariable("uid") Integer uid){
        Address address1 = addressService.getUserDefault(uid);
        if(address1 != null)
            return Result.succeed("获取默认地址成功",address1);
        else
            return Result.succeed("该用户没有默认地址",1);
    }


    @PostMapping("/addAddress")
    public Result addAddress(@RequestBody Address address){
        Address address1 = addressService.getUserDefault(address.getUid());
        if(address1 != null){               //判断该用户是否存在默认地址
            if(address.getIsDefault())      //判断新地址是不是要成为默认
            {
                addressService.updUserDefault(address1.getId());    //旧默认地址改为非默认
            }
        }
        addressService.addAddress(address);
        return Result.succeed("添加成功",1);
    }

    @PostMapping("/editAddress")
    public Result editAddress(@RequestBody Address address){
        Address address1 = addressService.getUserDefault(address.getUid());
        if(address1 != null){               //判断该用户是否存在默认地址
            if(address.getIsDefault() && address1.getId()!=address.getId())      //判断修改后的地址是不是要成为默认，是不是和当前默认地址相同
            {
                addressService.updUserDefault(address1.getId());    //旧默认地址改为非默认
            }
        }
        addressService.editAddress(address);
        return Result.succeed("修改成功",1);
    }

    @PostMapping("/detAddress")
    public Result detAddress(@RequestParam("id") Integer id){
        addressService.detAddress(id);
        return Result.succeed("删除成功",1);
    }
}
