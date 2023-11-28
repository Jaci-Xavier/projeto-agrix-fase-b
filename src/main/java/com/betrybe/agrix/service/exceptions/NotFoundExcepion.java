package com.betrybe.agrix.service.exceptions;

/**
 * NotFoundExcepion.
 */

public class NotFoundExcepion extends RuntimeException {
  public NotFoundExcepion(String message) {
    super(message);
  }
}
