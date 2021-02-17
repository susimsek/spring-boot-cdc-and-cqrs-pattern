package com.spring.denormalizer.listener;


import com.spring.denormalizer.event.DebeziumEventPayload;
import io.micrometer.core.annotation.Timed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
@Slf4j
public class EventDispatcher {

    final EventHandlerFactory handlerFactory;

    @KafkaListener(topics = "#{'${kafka.topics}'.split(',')}", containerFactory = "kafkaListenerContainerFactory")
    @Timed
   public void handleEvents(List<ConsumerRecord<String, DebeziumEventPayload>> records,
                            Acknowledgment acknowledgment) {
        log.debug("Request to process {} records", records.size());

        List<ConsumerRecord<String, DebeziumEventPayload>> sortedRecords = records.stream()
                .filter(r -> r.value()!=null)
                .sorted(Comparator.comparing(r -> r.value().getDate()))
                .collect(Collectors.toList());

        sortedRecords.forEach(record -> {

            log.debug("Request to handle {} event in the topic {}", record.value().getOperation(), record.topic());

            handlerFactory.getHandler(record.topic()).process(record.value());

        });


        acknowledgment.acknowledge();
    }
}