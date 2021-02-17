package com.spring.denormalizer.listener.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.denormalizer.event.enums.DebeziumEventPayloadOperation;
import com.spring.denormalizer.listener.EventHandler;
import com.spring.denormalizer.model.CityWriteDto;
import com.spring.denormalizer.service.CityService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@Slf4j
public class CityEventHandler extends AbstractSimpleEventHandler<CityWriteDto> implements EventHandler {

   final CityService cityService;

  public CityEventHandler(ObjectMapper mapper, CityService cityService) {
    super(mapper);
    this.cityService = cityService;
  }

  @PostConstruct
  public void init() {
    initActions();
  }

  @Override
  public void initActions() {
    actions.put(DebeziumEventPayloadOperation.CREATE, (before, after) -> cityService.save(after));
    actions.put(DebeziumEventPayloadOperation.UPDATE, (before, after) -> cityService.update(after));
    actions.put(DebeziumEventPayloadOperation.DELETE, (before, after) -> cityService.delete(before));
  }

}
