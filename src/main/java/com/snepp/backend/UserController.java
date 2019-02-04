package com.snepp.backend;

import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;
import com.snepp.backend.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by emre on 28.01.2019
 */
@RestController
@RequestMapping(value = "/v1")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
    return userService.register(registerRequest);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest){
    return userService.login(loginRequest);
  }
}
