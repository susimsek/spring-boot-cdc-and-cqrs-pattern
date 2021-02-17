package com.spring.reactive.service.query;

import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.read.GenericReactivePagedDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveCityQueryService {

    Flux<GenericReactivePagedDto<CityReadDto>> listCities(Pageable page);
    Mono<CityReadDto> getCity(String id);
}
