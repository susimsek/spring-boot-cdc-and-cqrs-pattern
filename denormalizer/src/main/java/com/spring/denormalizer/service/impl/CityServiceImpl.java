package com.spring.denormalizer.service.impl;

import com.spring.denormalizer.domain.City;
import com.spring.denormalizer.mapper.CityMapper;
import com.spring.denormalizer.model.CityWriteDto;
import com.spring.denormalizer.repository.CityRepository;
import com.spring.denormalizer.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
@Slf4j
public class CityServiceImpl implements CityService {

  final CityMapper cityMapper;
  final CityRepository cityRepository;

  @Override
  public void save(CityWriteDto cityWriteDto) {
    log.debug("Request to save user : {}", cityWriteDto);

    Assert.notNull(cityWriteDto, "City cannot be null");

    City city = cityMapper.cityWriteDtoToCity(cityWriteDto);
    cityRepository.save(city);

  }

  @Override
  public void update(CityWriteDto cityWriteDto) {
    log.debug("Request to update the user : {}", cityWriteDto);

    Assert.notNull(cityWriteDto, "City cannot be null");
    Assert.hasText(cityWriteDto.getId(), "City id cannot be null/empty");

    City existingCity=cityRepository.findById(cityWriteDto.getId()).orElseThrow(() -> new ResourceNotFoundException("City", "id", cityWriteDto.getId()));

    existingCity.setName(cityWriteDto.getName());
    existingCity.setPopulation(cityWriteDto.getPopulation());
    existingCity.setVersion(cityWriteDto.getVersion());
    existingCity.setUpdatedDate(cityWriteDto.getUpdatedDate());
    existingCity.setUpdatedBy(cityWriteDto.getUpdatedBy());

    cityRepository.save(existingCity);
  }

  @Override
  public void delete(CityWriteDto cityWriteDto) {
    log.debug("Request to delete the user : {}", cityWriteDto);

    Assert.notNull(cityWriteDto, "City cannot be null");
    Assert.hasText(cityWriteDto.getId(), "City id cannot be null/empty");

    City existingCity=cityRepository.findById(cityWriteDto.getId()).orElseThrow(() -> new ResourceNotFoundException("City", "id", cityWriteDto.getId()));

    cityRepository.delete(existingCity);

  }
}