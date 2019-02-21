package com.snepp.backend.v1.controller;

import com.snepp.backend.v1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import static com.snepp.backend.v1.security.SecurityConstants.TOKEN_PREFIX;

/**
 * Created by emre on 20.02.2019
 */
public abstract class BaseController{
  @Autowired
  private JwtTokenProvider tokenProvider;

  public String getUserIdFromHeader(HttpHeaders headers) {
    return tokenProvider
        .getIdFromToken(headers.get("Authorization")
            .get(0)
            .replace(TOKEN_PREFIX, ""));
  }
}
