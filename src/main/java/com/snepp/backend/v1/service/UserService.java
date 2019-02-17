package com.snepp.backend.v1.service;

import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;

/**
 * Created by emre on 28.01.2019
 */
public interface UserService {
  RegisterResponse register(RegisterRequest registerRequest);
  LoginResponse login(LoginRequest loginRequest);
  UserEntity findById(String userId);
}
