package com.buddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.buddy.model.Users;
import com.buddy.service.UsersService;
 

 
@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsersControllerTests {
 
  @MockBean
  UsersService userService;
 
  @Autowired
  MockMvc mockMvc;
 
  @Test
  public void testfindAll() throws Exception {
    Users user = Users.builder().withId(1).withName("testName1").withEmail("testName1@com.com").build();
    List<Users> users = Arrays.asList(user);
 
    Mockito.when(userService.getUsers()).thenReturn(users);
 
    mockMvc.perform(get("/api/v1/buddy-system/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].name", Matchers.is("testName1")));
  }
 
}