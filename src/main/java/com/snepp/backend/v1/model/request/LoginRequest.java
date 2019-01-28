package com.snepp.backend.v1.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonDeserialize
public class LoginRequest {
  private String email;
  private String password;
}
