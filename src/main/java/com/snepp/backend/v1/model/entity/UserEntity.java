package com.snepp.backend.v1.model.entity;

import com.snepp.backend.v1.model.Role;
import com.snepp.backend.v1.model.Share;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by emre on 28.01.2019
 */
@Builder
@Getter
@Setter
@Document(collection = "user")
public class UserEntity {
  @Id
  private ObjectId id;
  private String name;
  private String lastName;
  private String email;
  private String password;
  private Long registrationAt;
  private List<Share> userTeams;
  private List<Role> roles;

  @Override
  public String toString() {
    return "UserEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", registrationAt=" + registrationAt +
        ", userTeams=" + userTeams +
        ", roles=" + roles +
        '}';
  }
}
