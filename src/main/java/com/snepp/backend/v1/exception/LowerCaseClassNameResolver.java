package com.snepp.backend.v1.exception;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * Created by emre on 26.09.2019
 */
public class LowerCaseClassNameResolver extends TypeIdResolverBase {
  @Override
  public String idFromValue(Object value) {
    return value.getClass().getSimpleName().toLowerCase();
  }

  @Override
  public String idFromValueAndType(Object value, Class<?> suggestedType) {
    return idFromValue(value);
  }

  @Override
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }
}
