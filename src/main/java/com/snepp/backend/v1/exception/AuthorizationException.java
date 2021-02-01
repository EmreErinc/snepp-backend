package com.snepp.backend.v1.exception;

/**
 * Created by emre on 29.09.2019
 */
public class AuthorizationException extends Exception {
  public AuthorizationException() {
    super(AuthorizationException.generateMessage());
  }

  private static String generateMessage() {
    return "You're not authority for this operation";
  }
}
