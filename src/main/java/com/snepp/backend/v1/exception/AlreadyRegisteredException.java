package com.snepp.backend.v1.exception;

/**
 * Created by emre on 29.09.2019
 */
public class AlreadyRegisteredException extends Exception {
  public AlreadyRegisteredException() {
    super(AlreadyRegisteredException.generateMessage());
  }

  private static String generateMessage() {
    return "User already registered";
  }
}
