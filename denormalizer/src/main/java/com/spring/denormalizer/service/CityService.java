package com.spring.denormalizer.service;

import com.spring.denormalizer.model.CityWriteDto;

public interface CityService {
  void save(CityWriteDto cityWriteDto);

  void update(CityWriteDto cityWriteDto);

  void delete(CityWriteDto cityWriteDto);
}