package com.snepp.backend.v1.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by emre on 29.09.2019
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class Response<T> {
  private T result;

  public Response(T result){
    this.result = result;
  }
}