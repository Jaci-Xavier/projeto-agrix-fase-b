package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropsRepositories;
import com.betrybe.agrix.model.repositories.FarmsRepositories;
import com.betrybe.agrix.service.exceptions.NotFoundExcepion;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */

@Service
public class FarmService {
  private final FarmsRepositories farmsRepository;
  private final CropsRepositories cropsRepository;

  /**
   * Constructor.
   */

  public FarmService(FarmsRepositories farmsRepository, CropsRepositories cropsRepository) {
    this.farmsRepository = farmsRepository;
    this.cropsRepository = cropsRepository;
  }

  /**
   * Create a new farm.
   */

  public Farm createFarm(Farm farm) {
    return farmsRepository.save(farm);
  }

  /**
   * Get all farms.
   */

  public List<Farm> getAllFarms() {
    return farmsRepository.findAll();
  }

  /**
   * Get a farm by id.
   */

  public Farm getFarmById(Long id) {
    Optional<Farm> farm = farmsRepository.findById(id);

    if (farm.isEmpty()) {
      throw new NotFoundExcepion("Fazenda não encontrada!");
    }

    return farm.get();
  }

  /**
   * Create crop.
   */

  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = this.getFarmById(farmId);

    crop.setFarm(farm);

    Crop newCrop = cropsRepository.save(crop);
    farm.getCrops().add(newCrop);

    this.createFarm(farm);

    return newCrop;
  }

  /**
   * Get crops by farmId.
   */

  public List<Crop> getCropsByFarmId(Long farmId) {
    Farm farm = this.getFarmById(farmId);

    return farm.getCrops();
  }

}
