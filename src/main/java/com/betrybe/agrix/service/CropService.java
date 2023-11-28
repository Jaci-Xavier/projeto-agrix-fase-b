package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.repositories.CropsRepositories;
import com.betrybe.agrix.service.exceptions.NotFoundExcepion;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * CropService.
 */

@Service
public class CropService {
  private final CropsRepositories cropRepository;

  /**
   * Constructor.
   */

  public CropService(CropsRepositories cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * Get all crops.
   */

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Get crops by id.
   */

  public Crop getCropById(Long id) {

    Optional<Crop> crop = cropRepository.findById(id);

    if (crop.isEmpty()) {
      throw new NotFoundExcepion("Plantação não encontrada!");
    }

    return crop.get();
  }

  /**
   * Get crops by harvest date interval.
   */

  public List<Crop> getCropsToInterval(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

}
