package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Fertilizer;

/**
 * FertilizerDto.
 */

public record FertilizerDto(Long id, String name, String brand, String composition) {
  public Fertilizer convertToFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }
  
}
