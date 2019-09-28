package com.snepp.backend.v1.service;

import com.snepp.backend.v1.model.Role;
import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.Info;
import com.snepp.backend.v1.model.response.LoginResponse;
import com.snepp.backend.v1.model.response.RegisterResponse;
import com.snepp.backend.v1.repository.UserRepository;
import com.snepp.backend.v1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * Created by emre on 28.01.2019
 */
@Service(value = "userService")
@Configuration
@Component
public class UserServiceImpl implements UserService, UserDetailsService {
  private final JwtTokenProvider tokenProvider;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(JwtTokenProvider tokenProvider, UserRepository userRepository) {
    this.tokenProvider = tokenProvider;
    this.userRepository = userRepository;
  }

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {

    if (!userRepository.isExistsEmail(registerRequest.getEmail())) {
      UserEntity userEntity = UserEntity.builder()
          .name(registerRequest.getName())
          .lastName(registerRequest.getLastName())
          .email(registerRequest.getEmail())
          .password(registerRequest.getPassword())
          .registrationAt(Instant.now().toEpochMilli())
          .roles(registerRequest.getRoles())
          .build();
      return RegisterResponse.builder()
          .id(userRepository.saveUser(userEntity).toString())
          .token(generateToken(userEntity.getId().toString(), userEntity.getRoles()))
          .info(Info.builder()
              .email(userEntity.getEmail())
              .name(userEntity.getName())
              .lastName(userEntity.getLastName())
              .roles(userEntity.getRoles())
              .build())
          .build();
    } else {
      throw new RuntimeException("User Already Registered");
    }

  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    if (userRepository.isExistsEmail(loginRequest.getEmail())) {
      UserEntity userEntity = userRepository.findUserWithEmailAndPassword(loginRequest);
      return LoginResponse.builder()
          .id(userEntity.getId().toString())
          .token(generateToken(userEntity.getId().toString(), userEntity.getRoles()))
          .info(Info.builder()
              .email(userEntity.getEmail())
              .name(userEntity.getName())
              .lastName(userEntity.getLastName())
              .roles(userEntity.getRoles())
              .build())
          .build();
    } else {
      throw new RuntimeException("User Not Found");
    }
  }

  private String generateToken(String userId, List<Role> roles) {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
    return tokenProvider.generateToken(userId, authorities);
  }

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    //UserEntity userEntity = userRepository.findByEmail(email);
    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null) {
      try {
        throw new Exception("User Not Found");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), getAuthority(userEntity));
  }

  private Set getAuthority(UserEntity userEntity) {
    Set authorities = new HashSet<>();
    userEntity.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
    return authorities;
  }
}
