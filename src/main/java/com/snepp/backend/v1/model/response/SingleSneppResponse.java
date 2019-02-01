package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.snepp.backend.v1.model.SneppType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * Created by emre on 01.02.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class SingleSneppResponse {
  public ObjectId id;
  public String name;
  public String description;
  public String language;
  public String snippet;
  public SneppType type;
  public String ownerId;
}
