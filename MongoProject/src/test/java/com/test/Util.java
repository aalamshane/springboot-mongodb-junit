package com.test;

import com.mongo.entity.Address;
import com.mongo.entity.User;

public class Util {

    public static User createUserDetails() {
        User user = new User();
        user.setUserId("5eac75540bb8537d677e4b45");
        user.setRollno(3437268);
        user.setName("shane aalam");
        user.setMobno(99999999);
        Address address = new Address();
        address.setPincode(21212);
        address.setHousename("myhouse");
        user.setAddress(address);
        return user;
    }
}
