package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.SneppEntity;
import com.snepp.backend.v1.model.request.SneppUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Instant;
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
  public Optional<SneppEntity> update(String id, SneppUpdateRequest request) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));

    SneppEntity foundEntity = mongoTemplate.findOne(query, SneppEntity.class);
    foundEntity.setName(request.name);
    foundEntity.setDescription(request.description);
    foundEntity.setSnippet(request.snippet);
    foundEntity.setType(request.type);
    foundEntity.setLanguage(request.language);
    foundEntity.setUpdatedAt(Instant.now().toEpochMilli());

    return Optional.of(mongoTemplate.save(foundEntity));
  }

  @Override
  public Boolean deleteById(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return mongoTemplate.remove(query, SneppEntity.class).wasAcknowledged();
  }

  @Override
  public boolean isExists(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return mongoTemplate.exists(query, SneppEntity.class);
  }

  @Override
  public boolean isAuthorized(String userId, String sneppId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(sneppId).and("ownerId").is(userId));
    return mongoTemplate.exists(query, SneppEntity.class);
  }
}