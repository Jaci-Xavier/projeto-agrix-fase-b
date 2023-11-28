package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 * FarmDto.
 */

public record FarmDto(Long id, String name, Double size) {
  public Farm convertToFarm() {
    return new Farm(id, name, size);
  }
}