package com.snepp.backend.v1.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.management.relation.Role;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
public class Share {
  private ObjectId id;
  private Role role;
}
