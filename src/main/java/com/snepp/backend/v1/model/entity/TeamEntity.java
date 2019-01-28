package com.snepp.backend.v1.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by emre on 28.01.2019
 */
@Getter
@Setter
@Document(collection = "team")
public class TeamEntity {
  @Id
  private ObjectId id;
  private String name;
  private Long createdAt;
  private ObjectId managerId;
}
