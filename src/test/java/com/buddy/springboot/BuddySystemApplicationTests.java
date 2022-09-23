package com.buddy.springboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import com.buddy.controller.AppController;
import com.buddy.controller.RedirectController;
import com.buddy.controller.UsersController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BuddySystemApplicationTests {

	@Autowired
	UsersController userController;

	@Autowired
	RedirectController redirectController;
	
	@Autowired
	AppController appController;
	
	@Test
	public void contextLoads() {
	    // empty test that would fail if our Spring configuration does not load correctly

	}

}
