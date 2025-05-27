package com.x10.workshop6;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.x10.workshop6.controller.UserController;
import com.x10.workshop6.service.UserService;

@SpringBootTest
class Workshop6ApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		assertNotNull(userController);
		assertNotNull(userService);
	}

}
