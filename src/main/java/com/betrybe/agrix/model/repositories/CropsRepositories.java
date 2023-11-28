package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CropsRepositories.
 */

public interface CropsRepositories extends JpaRepository<Crop, Long> {

}
