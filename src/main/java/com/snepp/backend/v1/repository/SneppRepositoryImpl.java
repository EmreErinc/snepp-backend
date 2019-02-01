package com.snepp.backend.v1.repository;

import com.snepp.backend.v1.model.entity.SneppEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

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
  public SneppEntity findById(String id) {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    return mongoTemplate.findOne(query, SneppEntity.class);
  }

  @Override
  public List<SneppEntity> listByOwnerId(String ownerId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("ownerId").is(ownerId));
    return mongoTemplate.find(query, SneppEntity.class);
  }
}
