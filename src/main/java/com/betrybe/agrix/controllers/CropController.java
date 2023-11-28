package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CropController.
 */

@Controller
@RequestMapping("/crops")

public class CropController {
  private final CropService cropService;

  /**
   * Constructor.
   */

  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Get all crops.
   */

  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    List<CropDto> cropsDto = crops.stream().map(crop -> new CropDto(
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId()
    )).toList();

    return new ResponseEntity<>(cropsDto, HttpStatus.OK);
  }

  /**
   * Get crops by id.
   */

  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);

    CropDto cropDto = new CropDto(
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId()
    );

    return new ResponseEntity<>(cropDto, HttpStatus.OK);
  }

}
