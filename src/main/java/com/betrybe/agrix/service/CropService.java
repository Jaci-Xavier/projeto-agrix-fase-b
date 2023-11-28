package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropsRepositories;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
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
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor.
   */

  public CropService(CropsRepositories cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
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

  /**
   * crop and fertilizer associate.
   */

  public String associateFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> crop = cropRepository.findById(cropId);

    if (crop.isEmpty()) {
      throw new NotFoundExcepion("Plantação não encontrada!");
    }

    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);

    if (fertilizer.isEmpty()) {
      throw new NotFoundExcepion("Fertilizante não encontrado!");
    }

    Crop cropToAssociate = crop.get();
    Fertilizer fertilizerToAssociate = fertilizer.get();

    cropToAssociate.getFertilizers().add(fertilizerToAssociate);

    cropRepository.save(cropToAssociate);

    return "Fertilizante e plantação associados com sucesso!";

  }

  /**
   * Get all fertilizer by cropId.
   */

  public List<Fertilizer> getAllFertilizerByCropId(Long cropId) {
    Optional<Crop> crop = cropRepository.findById(cropId);

    if (crop.isEmpty()) {
      throw new NotFoundExcepion("Plantação não encontrada!");
    }

    return crop.get().getFertilizers();
  }

}
