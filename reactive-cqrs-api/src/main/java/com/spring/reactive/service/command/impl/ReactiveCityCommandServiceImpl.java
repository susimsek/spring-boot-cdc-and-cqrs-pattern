package com.spring.reactive.service.command.impl;

import com.spring.reactive.domain.write.CityCommand;
import com.spring.reactive.exception.ResourceNotFoundException;
import com.spring.reactive.mapper.CityMapper;
import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.write.CityWriteDto;
import com.spring.reactive.repository.command.ReactiveCityCommandRepository;
import com.spring.reactive.service.command.ReactiveCityCommandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class ReactiveCityCommandServiceImpl implements ReactiveCityCommandService {

    final CityMapper cityMapper;
    final ReactiveCityCommandRepository reactiveCityCommandRepository;

    @Override
    public Mono<CityReadDto> createCity(CityWriteDto cityWriteDto) {
        CityCommand cityCommand = cityMapper.cityWriteDtoToCityCommand(cityWriteDto);
        return reactiveCityCommandRepository.save(cityCommand)
                .map(cityMapper::cityCommandToCityReadDto);
    }

    @Override
    public Mono<CityReadDto> updateCity(String id, CityWriteDto cityWriteDto) {
        return reactiveCityCommandRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("City", "id", id)))
                .flatMap(existingCity -> {
                    existingCity.setName(cityWriteDto.getName());
                    existingCity.setPopulation(cityWriteDto.getPopulation());
                    return reactiveCityCommandRepository.save(existingCity);
                })
                .map(cityMapper::cityCommandToCityReadDto);
    }

    @Override
    public Mono<Boolean> deleteCity(String id) {
        return reactiveCityCommandRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("City", "id", id)))
                .flatMap(reactiveCityCommandRepository::delete)
                .then(Mono.just(true));
    }
}
