package com.snepp.backend.v1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by emre on 26.09.2019
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  /**
   * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
    Error error = new Error(NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(error, error.getStatus());
  }

  @ExceptionHandler(AuthorizationException.class)
  protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex) {
    Error error = new Error(UNAUTHORIZED, ex.getMessage());
    return new ResponseEntity<>(error, error.getStatus());
  }

  @ExceptionHandler(AlreadyRegisteredException.class)
  protected ResponseEntity<Object> handleAlreadyRegisteredException(AlreadyRegisteredException ex) {
    Error error = new Error(HttpStatus.CONFLICT, ex.getMessage());
    return new ResponseEntity<>(error, error.getStatus());
  }
}
