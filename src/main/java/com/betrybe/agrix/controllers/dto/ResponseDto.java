package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 * ResponseDto.
 */

public record ResponseDto<T>(Farm farm) {}
