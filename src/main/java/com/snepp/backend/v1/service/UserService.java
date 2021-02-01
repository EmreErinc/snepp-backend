package com.snepp.backend.v1.service;

import com.snepp.backend.v1.exception.AlreadyRegisteredException;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;

/**
 * Created by emre on 28.01.2019
 */
public interface UserService {
  RegisterResponse register(RegisterRequest registerRequest) throws AlreadyRegisteredException;

  LoginResponse login(LoginRequest loginRequest) throws Exception;
}
