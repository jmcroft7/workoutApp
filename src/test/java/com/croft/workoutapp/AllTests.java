package com.croft.workoutapp;

import com.croft.workoutapp.service.UserServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SelectPackages({"com.croft.workoutapp.service"})
@SelectClasses(UserServiceTest.class)
@Suite
public class AllTests {
}
