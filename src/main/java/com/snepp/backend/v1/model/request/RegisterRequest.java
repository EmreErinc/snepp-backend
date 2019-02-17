package com.snepp.backend.v1.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snepp.backend.v1.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@JsonDeserialize
public class RegisterRequest {
  @NotBlank
  @Size(min = 1, max = 15)
  private String name;

  @NotBlank
  @Size(min = 1, max = 15)
  private String lastName;

  @NotBlank
  @Email
  @Size(min = 1, max = 30)
  private String email;

  @NotBlank
  @Size(min = 1, max = 15)
  private String password;

  @NotBlank
  private List<Role> roles;
}
