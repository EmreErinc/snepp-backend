package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.RegisterResponse;
import org.bson.types.ObjectId;

/**
 * Created by emre on 28.01.2019
 */
public interface UserRepository{
  ObjectId saveUser(UserEntity userEntity);
  boolean isExistsEmail(String email);
  UserEntity findUserWithEmailAndPassword(LoginRequest loginRequest);
  UserEntity findByUserId(String userId);
  UserEntity findByEmail(String email);
}
