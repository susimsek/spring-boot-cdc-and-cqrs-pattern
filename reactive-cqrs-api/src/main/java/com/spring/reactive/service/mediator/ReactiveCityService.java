package com.spring.reactive.service.mediator;

import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.read.GenericReactivePagedDto;
import com.spring.reactive.model.write.CityWriteDto;
import com.spring.reactive.service.command.ReactiveCityCommandService;
import com.spring.reactive.service.query.ReactiveCityQueryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class ReactiveCityService implements ReactiveCityCommandService, ReactiveCityQueryService {

    final ReactiveCityCommandService reactiveCityCommandService;
    final ReactiveCityQueryService reactiveCityQueryService;

    @Override
    public Mono<CityReadDto> createCity(CityWriteDto cityWriteDto) {
        return reactiveCityCommandService.createCity(cityWriteDto);
    }

    @Override
    public Mono<CityReadDto> updateCity(String id, CityWriteDto cityWriteDto) {
        return reactiveCityCommandService.updateCity(id,cityWriteDto);
    }

    @Override
    public Mono<Boolean> deleteCity(String id) {
        return reactiveCityCommandService.deleteCity(id);
    }

    @Override
    public Flux<GenericReactivePagedDto<CityReadDto>> listCities(Pageable page) {
        return reactiveCityQueryService.listCities(page);
    }

    @Override
    public Mono<CityReadDto> getCity(String id) {
        return reactiveCityQueryService.getCity(id);
    }
}
