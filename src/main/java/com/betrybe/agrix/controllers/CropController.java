package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
          crop.getHarvestDate(), crop.getFarm().getId()
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
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
          crop.getHarvestDate(), crop.getFarm().getId()
    );

    return new ResponseEntity<>(cropDto, HttpStatus.OK);
  }

  /**
   * Crop search.
   */

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsToInterval(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> crops = cropService.getCropsToInterval(start, end);

    List<CropDto> cropsDto = crops.stream().map(crop -> new CropDto(
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
          crop.getHarvestDate(), crop.getFarm().getId()
    )).collect(Collectors.toList());

    return new ResponseEntity<>(cropsDto, HttpStatus.OK);
  }

  /**
   * Crop and fertilizer associate.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    String message = cropService.associateFertilizer(cropId, fertilizerId);

    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  /**
   * Get fertilizer by cropId.
   */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizerByCropId(@PathVariable Long cropId) {
    List<Fertilizer> fertilizersDto = cropService.getAllFertilizerByCropId(cropId);

    List<FertilizerDto> fertilizers = fertilizersDto.stream().map(fertilizer -> new FertilizerDto(
        fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(), fertilizer.getComposition()
    )).toList();

    return new ResponseEntity<>(fertilizers, HttpStatus.OK);
  }

}
