package com.snepp.backend.v1.model.entity;

import com.snepp.backend.v1.model.Share;
import com.snepp.backend.v1.model.SneppType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@Document(collection = "snepp")
public class SneppEntity {
  @Id
  private ObjectId id;
  private String name;
  private String description;
  private String language;
  private String snippet;
  private Long createdAt;
  private Long updatedAt;
  private List<Share> shareList;
  private SneppType type;
}
