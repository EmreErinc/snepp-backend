package com.snepp.backend;

import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.RegisterResponse;
import com.snepp.backend.v1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by emre on 29.01.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @Test
  public void shouldPassRegister(){
    /*RegisterRequest registerRequest = RegisterRequest.builder()
        .name("test-name")
        .lastName("test-lm")
        .email("test@mail.com")
        .password("test")
        .build();

    RegisterResponse registerResponse = userService.register(registerRequest);
    assertNotNull(registerResponse);*/
  }
}
