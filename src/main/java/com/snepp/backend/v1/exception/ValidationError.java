package com.snepp.backend.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by emre on 28.09.2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ValidationError extends SubError {
  private String object;
  private String field;
  private Object rejectedValue;
  private String message;

  ValidationError(String object, String message) {
    this.object = object;
    this.message = message;
  }
}
