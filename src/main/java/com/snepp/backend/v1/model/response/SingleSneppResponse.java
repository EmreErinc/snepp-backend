package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.snepp.backend.v1.model.SneppType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by emre on 01.02.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class SingleSneppResponse {
  private String name;
  private String description;
  private String language;
  private String snippet;
  private SneppType type;
  private String ownerId;
}
