package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FarmsRepositories.
 */


public interface FarmsRepositories extends JpaRepository<Farm, Long> {}