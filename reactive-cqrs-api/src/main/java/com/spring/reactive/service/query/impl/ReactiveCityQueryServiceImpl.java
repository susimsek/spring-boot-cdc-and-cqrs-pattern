package com.spring.reactive.service.query.impl;

import com.spring.reactive.domain.read.CityQuery;
import com.spring.reactive.exception.ResourceNotFoundException;
import com.spring.reactive.mapper.CityMapper;
import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.read.GenericReactivePagedDto;
import com.spring.reactive.repository.query.ReactiveCityQueryRepository;
import com.spring.reactive.service.query.ReactiveCityQueryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class ReactiveCityQueryServiceImpl implements ReactiveCityQueryService {

    final CityMapper cityMapper;
    final ReactiveCityQueryRepository reactiveCityQueryRepository;
    final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    @Override
    public Flux<GenericReactivePagedDto<CityReadDto>> listCities(Pageable page) {
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        query.withPageable(page);

        return reactiveElasticsearchTemplate.search(query.build(),
                CityQuery.class,
                IndexCoordinates.of("cities")
        ).map(cityQuerySearchHit -> new GenericReactivePagedDto<CityReadDto>(
                cityQuerySearchHit.getIndex(),
                cityQuerySearchHit.getId(),
                cityQuerySearchHit.getScore(),
                cityQuerySearchHit.getSortValues().toArray(),
                cityQuerySearchHit.getHighlightFields(),
                cityMapper.cityQueryToCityReadDto(cityQuerySearchHit.getContent())
                ));
    }

    @Override
    public Mono<CityReadDto> getCity(String id) {
        return reactiveCityQueryRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("City", "id", id)))
                .map(cityMapper::cityQueryToCityReadDto);
    }
}
