package com.spring.reactive.repository.query;

import com.spring.reactive.domain.read.CityQuery;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ReactiveCityQueryRepository extends ReactiveElasticsearchRepository<CityQuery, String> {
}