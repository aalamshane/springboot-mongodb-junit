package com.mongo.service;

import com.mongo.entity.User;

import java.util.List;



public interface UserService {

    List<User> getAllUsers();

    User getUserById(int userId);

    User addNewUser(User user);

}
