package com.mongo.controller;

import com.mongo.entity.User;
import com.mongo.service.UserService;
import com.mongo.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")
    public User addUser(@RequestBody User user) {
        if (user!=null) {
            return userService.addNewUser(user);
        }
        else {
            throw new RuntimeException("Please provide the user detail to create new user");
        }

    }

    @RequestMapping(value = "/getuser/{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        int usrId = 0;
        usrId = Integer.valueOf(userId);
        User user = userService.getUserById(usrId);
        if (user!=null){
            return userService.getUserById(usrId);
        }
        else
            throw new RuntimeException("No user found with userId: " +  userId);
    }

    @GetMapping(value = "/getallusers")
    public List<User> getAllUser() {
        return userService.getAllUsers();

    }
}
