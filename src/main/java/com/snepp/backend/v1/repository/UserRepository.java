package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.RegisterResponse;

/**
 * Created by emre on 28.01.2019
 */
public interface UserRepository{
  RegisterResponse saveUser(UserEntity userEntity);
  boolean existsEmail(String email);
  UserEntity findUserWithEmail(String email);
  UserEntity findUserWithEmailAndPassword(LoginRequest loginRequest);
}
