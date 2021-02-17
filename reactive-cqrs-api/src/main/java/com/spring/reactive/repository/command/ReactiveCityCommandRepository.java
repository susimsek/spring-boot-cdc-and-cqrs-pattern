package com.spring.reactive.repository.command;

import com.spring.reactive.domain.write.CityCommand;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveCityCommandRepository extends ReactiveMongoRepository<CityCommand, String> {
}