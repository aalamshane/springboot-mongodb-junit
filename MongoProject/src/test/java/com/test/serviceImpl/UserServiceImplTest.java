package com.test.serviceImpl;

import com.mongo.entity.User;
import com.mongo.exception.UserNotFoundException;
import com.mongo.service.UserService;
import com.mongo.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static com.test.Util.createUserDetails;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getUser_Valid() throws Exception {
        User user = createUserDetails();
        Query query = new Query();
        query.addCriteria(Criteria.where("rollno").is(3437268));
        when(mongoTemplate.findOne(query, User.class)).thenReturn(user);
        User result = userServiceImpl.getUserById(3437268);
        assertEquals(result.getRollno(), user.getRollno());
        assertEquals(result.getName(), user.getName());
        assertEquals(result.getMobno(), user.getMobno());
    }

    @Test
    final void getUser_inValid() throws Exception {
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> {
            Query query = new Query();
            query.addCriteria(Criteria.where("rollno").is(3437268));
            when(mongoTemplate.findOne(query, User.class)).thenReturn(null);
            userServiceImpl.getUserById(3437268);
        });

        assertTrue(userNotFoundException.getMessage().contains("User not found"));
    }
}
