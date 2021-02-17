package com.spring.reactive.service.command;

import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.write.CityWriteDto;
import reactor.core.publisher.Mono;

public interface ReactiveCityCommandService {

    Mono<CityReadDto> createCity(CityWriteDto cityWriteDto);
    Mono<CityReadDto> updateCity(String id,CityWriteDto cityWriteDto);
    Mono<Boolean> deleteCity(String id);
}
