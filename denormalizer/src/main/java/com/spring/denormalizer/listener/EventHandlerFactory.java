package com.spring.denormalizer.listener;

import com.google.common.collect.Maps;
import com.spring.denormalizer.listener.handler.CityEventHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@RequiredArgsConstructor
@Slf4j
public class EventHandlerFactory {

  final CityEventHandler cityEventHandler;

  final Map<String, EventHandler> handlers = Maps.newConcurrentMap();

  @PostConstruct
  public void init() {
    log.debug("Initializing events handlers");

    handlers.put("cities", cityEventHandler);
  }

  public EventHandler getHandler(String topicName) {
    Assert.hasText(topicName, "Topic name cannot be empty");

    String tableName = topicName.substring(topicName.lastIndexOf(".") + 1).toLowerCase(Locale.ROOT);

    log.debug("Request to use a handler for the table {}", tableName);

    return Optional.ofNullable(handlers.get(tableName))
            .orElseThrow(() -> new IllegalArgumentException("No suitable handler was found for the topic " + topicName));
  }
}