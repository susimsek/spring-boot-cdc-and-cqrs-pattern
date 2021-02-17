package com.spring.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import reactor.core.publisher.Mono;

@EnableReactiveMongoAuditing
@Configuration
public class MongodbAuditConfig {

    @Bean
    public ReactiveAuditorAware<String> auditorProvider() {
        return () -> Mono.just("admin");
    }
}
