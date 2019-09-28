package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by emre on 29.01.2019
 */
@Slf4j
@Configuration
public class SneppRepositoryImpl implements SneppRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public SneppEntity save(SneppEntity sneppEntity) {
    SneppEntity snepp = mongoTemplate.save(sneppEntity);
    log.info("Snepp Saved. Result : {}", snepp);
    return snepp;
  }

  @Override
  public Optional<SneppEntity> findById(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return Optional.ofNullable(mongoTemplate.findOne(query, SneppEntity.class));
  }

  @Override
  public Optional<List<SneppEntity>> listByOwnerId(String ownerId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("ownerId").is(ownerId));
    return Optional.of(mongoTemplate.find(query, SneppEntity.class));
  }

  @Override
  public Optional<SneppEntity> update(SneppUpdateRequest request) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(request.id));

    SneppEntity foundEntity = mongoTemplate.findOne(query, SneppEntity.class);
    foundEntity.builder()
        .name(request.name)
        .description(request.description)
        .snippet(request.snippet)
        .type(request.type)
        .language(request.language)
        .build();

    return Optional.of(mongoTemplate.save(foundEntity));
  }

  @Override
  public Boolean deleteById(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return mongoTemplate.remove(query, SneppEntity.class).wasAcknowledged();
  }

  @Override
  public Boolean isExists(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return mongoTemplate.exists(query, SneppEntity.class);
  }
}
