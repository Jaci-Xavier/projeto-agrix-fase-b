package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.ErrorDto;
import com.betrybe.agrix.service.exceptions.NotFoundExcepion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice.
 */

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice {
  /**
   * Handle not found exception.
   */

  @ExceptionHandler(NotFoundExcepion.class)
  public ResponseEntity<ErrorDto> handleNotFound(NotFoundExcepion exception) {
    return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
  }
}