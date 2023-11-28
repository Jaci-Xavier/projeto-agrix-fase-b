package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import org.springframework.stereotype.Service;

/**
 * FertilizerService.
 */

@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor.
   */

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create fertilizers.
   */

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Get all fertilizers.
   */

  public Iterable<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }
}
