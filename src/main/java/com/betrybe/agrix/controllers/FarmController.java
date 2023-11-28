package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FarmController.
 */

@Controller
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  /**
   * Constructor.
   */

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create a new farm.
   */

  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm farm = farmService.createFarm(farmDto.convertToFarm());

    FarmDto newFarmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());

    return new ResponseEntity<>(newFarmDto, HttpStatus.CREATED);
  }

  /**
   * Get all farms.
   */

  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    List<FarmDto> farmsDto = farms.stream().map(farm -> new FarmDto(
        farm.getId(), farm.getName(), farm.getSize()
    )).toList();

    return new ResponseEntity<>(farmsDto, HttpStatus.OK);
  }

  /**
   * Get a farm by id.
   */

  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);

    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());

    return new ResponseEntity<>(farmDto, HttpStatus.OK);
  }

  /**
   * Create a new crop.
   */
   
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto
  ) {

    Crop crop = farmService.createCrop(farmId, cropDto.convertToCrop());

    CropDto newCropDto = new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate(), farmId
    );

    return new ResponseEntity<>(newCropDto, HttpStatus.CREATED);
  }

  /**
   * Get crops by farmId.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getCropsByFarmId(farmId);

    List<CropDto> cropsDto = crops.stream().map(crop -> new CropDto(
        crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(),
          crop.getHarvestDate(), farmId
    )).toList();

    return new ResponseEntity<>(cropsDto, HttpStatus.OK);
  }
}
