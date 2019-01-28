package com.snepp.backend.v1.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snepp.backend.v1.model.SneppType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonDeserialize
public class SneppRequest {
  public ObjectId id;
  public String name;
  public String description;
  public String language;
  public String snippet;
  public SneppType type;
}
