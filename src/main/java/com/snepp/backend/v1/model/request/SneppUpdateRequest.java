package com.snepp.backend.v1.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snepp.backend.v1.model.SneppType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by emre on 25.09.2019
 */
@Builder
@Getter
@Setter
@JsonDeserialize
public class SneppUpdateRequest {
  @NotBlank
  public String id;

  @NotBlank
  @Size(min = 1, max = 15)
  public String name;

  @Size(min = 1, max = 15)
  public String description;

  @Size(min = 1, max = 15)
  public String language;

  @NotBlank
  @Size(min = 1, max = 15)
  public String snippet;

  @NotBlank
  @Size(min = 1, max = 15)
  public SneppType type;
}
