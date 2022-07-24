package com.bobo.service;

import com.bobo.dao.AddressMapper;
import com.bobo.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;

    public void detAddress(Integer id){addressMapper.detAddress(id);}

    public List<Address> getAddress(Integer uid){return addressMapper.getAddress(uid);}

    public Address getAddressById(Integer id){return addressMapper.getAddressById(id);}

    public Address getUserDefault(Integer uid){return addressMapper.getUserDefault(uid);}

    public void updUserDefault(Integer id){addressMapper.updUserDefault(id);}

    public void addAddress(Address address){addressMapper.addAddress(address);}

    public void editAddress(Address address){addressMapper.editAddress(address);}
}
