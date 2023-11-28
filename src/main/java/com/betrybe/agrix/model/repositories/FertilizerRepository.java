package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FertilizerRepository.
 */

@Repository

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
