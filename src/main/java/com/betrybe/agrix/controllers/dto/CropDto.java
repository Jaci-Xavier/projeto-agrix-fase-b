package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;

/**
 * CropDto.
 */

public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
    LocalDate harvestDate, Long farmId) {
  public Crop convertToCrop() {
    return new Crop(id, name, plantedArea, plantedDate, harvestDate, null);
  }
  
}
