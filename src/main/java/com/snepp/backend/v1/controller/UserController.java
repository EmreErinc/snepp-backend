package com.snepp.backend.v1.controller;

import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;
import com.snepp.backend.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by emre on 28.01.2019
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/v1")
public class UserController {
  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
    //registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
    return userService.register(registerRequest);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    //loginRequest.setPassword(bCryptPasswordEncoder.encode(loginRequest.getPassword()));
    return userService.login(loginRequest);
  }
}
