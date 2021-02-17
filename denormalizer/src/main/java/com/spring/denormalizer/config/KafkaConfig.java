package com.spring.denormalizer.config;

import com.google.common.collect.ImmutableMap;
import com.spring.denormalizer.deserializer.DebeziumEventPayloadDeserializer;
import com.spring.denormalizer.event.DebeziumEventPayload;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@EnableKafka
public class KafkaConfig {


    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    String groupId;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DebeziumEventPayload>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DebeziumEventPayload> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(MANUAL);
        factory.setBatchListener(true);

        return factory;
    }

    @Bean
    public ConsumerFactory<String, DebeziumEventPayload> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
                new StringDeserializer(),
                new DebeziumEventPayloadDeserializer()
        );
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
                .put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DebeziumEventPayloadDeserializer.class)
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                .put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
                .put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10")
                .build();
    }

}
