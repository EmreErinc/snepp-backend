package com.snepp.backend.v1.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonDeserialize
public class LoginRequest {
  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;
}
