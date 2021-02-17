package com.spring.denormalizer.mapper;

import com.spring.denormalizer.domain.City;
import com.spring.denormalizer.model.CityWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {
    City cityWriteDtoToCity(CityWriteDto cityWriteDto);
}
