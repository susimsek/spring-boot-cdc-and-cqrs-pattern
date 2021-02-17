package com.spring.reactive.mapper;

import com.spring.reactive.domain.read.CityQuery;
import com.spring.reactive.domain.write.CityCommand;
import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.write.CityWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {

    CityReadDto cityCommandToCityReadDto(CityCommand cityCommand);
    CityCommand cityWriteDtoToCityCommand(CityWriteDto cityWriteDto);
    CityReadDto cityQueryToCityReadDto(CityQuery cityQuery);

}
