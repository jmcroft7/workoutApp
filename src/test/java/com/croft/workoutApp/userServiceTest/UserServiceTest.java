package com.croft.workoutApp.userServiceTest;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void createUser() {
        User _user = new User();
        _user.setEmail("j@c.com");
        _user.setPassword("asdfjkl;");
        _user.setFirstName("john");
        _user.setLastName("doe");
        userService.createUser(_user);

        User user2 = userService.findByEmail("j@c.com").get();
        assertEquals("john", user2.getFirstName());
    }
}
