package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CropsRepositories.
 */
@Repository
public interface CropsRepositories extends JpaRepository<Crop, Long> {
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
