package com.mongo.serviceImpl;

import com.mongo.entity.User;
import com.mongo.exception.UserNotFoundException;
import com.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User getUserById(int rollno) throws RuntimeException{
        Query query = new Query();
        query.addCriteria(Criteria.where("rollno").is(rollno));
        User user =  mongoTemplate.findOne(query, User.class);
        if (user!=null){
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User addNewUser(User user) {
        Assert.notNull(user, "Object to save must not be null!");

        return mongoTemplate.save(user);

    }

}
