package com.croft.workoutapp.service;

import com.croft.workoutapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testCreateUser() {
        User _user = new User();
        _user.setEmail("j@e.com");
        _user.setPassword("asdfjkl;");
        _user.setFirstName("john");
        _user.setLastName("doe");
        userService.createUser(_user);

        User user2 = userService.findByEmail("j@e.com").get();
        assertEquals("john", user2.getFirstName());
    }

    @ParameterizedTest
    @CsvSource({
            "3,j@c.com,asdfjkl;,John,Croft",
            "4,j@d.com,asdfjkl;,john,doe"
    })
    void testFindById(ArgumentsAccessor arguments) throws ArgumentAccessException, SQLException {
        System.out.println(this.toString());

        User expected = new User();
        expected.setId(arguments.getLong(0));
        expected.setEmail(arguments.getString(1));
        expected.setPassword(arguments.getString(2));
        expected.setFirstName(arguments.getString(3));
        expected.setLastName(arguments.getString(3));
        userService.createUser(expected);

        User actual = userService.findByEmail(arguments.getString(1)).get();
        assertEquals(expected, actual);
    }
}

