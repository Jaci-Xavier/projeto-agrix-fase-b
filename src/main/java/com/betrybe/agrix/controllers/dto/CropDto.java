package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Crop;

/**
 * CropDto.
 */

public record CropDto(Long id, String name, Double plantedArea, Long farmId) {
  public Crop convertToCrop() {
    return new Crop(id, name, plantedArea, null);
  }
  
}
