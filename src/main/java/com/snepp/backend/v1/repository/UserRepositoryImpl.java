package com.snepp.backend.v1.repository;

import com.mongodb.util.JSON;
import com.snepp.backend.v1.model.entity.UserEntity;
import com.snepp.backend.v1.model.request.LoginRequest;
import com.snepp.backend.v1.model.request.RegisterRequest;
import com.snepp.backend.v1.model.response.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


/**
 * Created by emre on 29.01.2019
 */
@Slf4j
@Configuration
public class UserRepositoryImpl implements UserRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public RegisterResponse saveUser(UserEntity userEntity) {
    UserEntity user = mongoTemplate.save(userEntity);
    log.info("User Saved. Result : {}", user);

    return RegisterResponse.builder().id(user.getId().toString()).token("asdas").build();
  }

  @Override
  public boolean existsEmail(String email) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));
    return mongoTemplate.exists(query, UserEntity.class);
  }

  @Override
  public UserEntity findUserWithEmail(String email) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));
    return mongoTemplate.findOne(query, UserEntity.class);
  }

  @Override
  public UserEntity findUserWithEmailAndPassword(LoginRequest loginRequest) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(loginRequest.getEmail()))
        .addCriteria(Criteria.where("password").is(loginRequest.getPassword()));
    return mongoTemplate.findOne(query, UserEntity.class);
  }
}
