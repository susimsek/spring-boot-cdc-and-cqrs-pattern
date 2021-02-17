package com.spring.denormalizer.repository;

import com.spring.denormalizer.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CityRepository extends ElasticsearchRepository<City, String> {
}
