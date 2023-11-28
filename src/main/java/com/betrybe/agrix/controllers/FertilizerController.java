package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FertilizerController.
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  public final FertilizerService fertilizerService;

  /**
   * Constructor.
   */

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create a new fertilizer.
   */

  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizerCreated = fertilizerService.createFertilizer(
        fertilizerDto.convertToFertilizer()
    );
    return new ResponseEntity<>(fertilizerCreated, HttpStatus.CREATED);
  }

  /**
   * Get all fertilizers.
   */

  @GetMapping()
  public List<FertilizerDto> getAllFertilizers() {
    Iterable<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    return ((List<Fertilizer>) fertilizers).stream().map(fertilizer -> new FertilizerDto(
        fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(), fertilizer.getComposition()
    )).collect(Collectors.toList());
  }


}
