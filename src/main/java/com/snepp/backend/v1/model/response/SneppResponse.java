package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class SneppResponse {
  public String id;
  public String name;
  public String snippet;
}
