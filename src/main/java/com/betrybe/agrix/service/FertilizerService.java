package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import com.betrybe.agrix.service.exceptions.NotFoundExcepion;

import java.util.Optional;
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

  /**
   * Get fertilizer by id.
   */

   public Fertilizer getFertilizerById(Long fertilizerId) {
     Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);

     if (fertilizer.isEmpty()) {
       throw new NotFoundExcepion("Fertilizante não encontrado!");
     }

     return fertilizer.get();
   }
}
