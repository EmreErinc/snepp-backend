package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.snepp.backend.v1.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by emre on 21.02.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class Info {
  private String name;
  private String lastName;
  private String email;
  private List<Role> roles;
}
