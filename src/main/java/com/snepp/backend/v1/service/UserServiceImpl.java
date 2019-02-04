package com.snepp.backend.v1.service;

import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;
import com.snepp.backend.v1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

/**
 * Created by emre on 28.01.2019
 */
@Configuration
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {
    if (!userRepository.existsEmail(registerRequest.getEmail())){
      UserEntity userEntity = UserEntity.builder()
          .name(registerRequest.getName())
          .lastName(registerRequest.getLastName())
          .email(registerRequest.getEmail())
          .password(registerRequest.getPassword())
          .registrationAt(Instant.now().toEpochMilli())
          .build();
      return userRepository.saveUser(userEntity);
    }
    return null;
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    LoginResponse loginResponse = LoginResponse.builder().build();

    if(userRepository.existsEmail(loginRequest.getEmail())){
      UserEntity userEntity = userRepository.findUserWithEmailAndPassword(loginRequest);

      loginResponse.setId(userEntity.getId().toString());
      loginResponse.setToken("wewretryut");
    }

    return loginResponse;
  }
}
