package com.spring.denormalizer.listener.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.spring.denormalizer.event.DebeziumEventPayload;
import com.spring.denormalizer.event.enums.DebeziumEventPayloadOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractSimpleEventHandler<T> {

  protected final Map<DebeziumEventPayloadOperation, BiConsumer<T, T>> actions = Maps.newConcurrentMap();

  private final ObjectMapper mapper;

  public abstract void initActions();

  @SuppressWarnings("unchecked")
  public void process(DebeziumEventPayload payload) {
    DebeziumEventPayloadOperation operation = payload.getOperation();
    String payloadBefore = payload.getBefore();
    String payloadAfter = payload.getAfter();
    String payloadPatch = payload.getPatch();
    String payloadFilter = payload.getFilter();

    log.debug("Request to handle {} event with payload that was {} and become {}", operation, payloadBefore, payloadAfter);

    Class<T> entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractSimpleEventHandler.class);

    if (Objects.isNull(entityClass)){
      throw new IllegalArgumentException("AbstractSimpleEventHandler should have a type a argument");
    }

    T before = null;
    T after = null;
    try {
      if(!payloadBefore.isBlank() && !payloadBefore.equals("null"))
      before = mapper.readValue(payloadBefore, entityClass);

      if(!payloadAfter.isBlank() && !payloadAfter.equals("null"))
      after = mapper.readValue(payloadAfter, entityClass);

      if(!payloadPatch.isBlank() && !payloadPatch.equals("null"))
        after =  mapper.readValue(payloadPatch, entityClass);

      if(!payloadFilter.isBlank() && !payloadFilter.equals("null"))
        before = mapper.readValue(payloadFilter, entityClass);
    } catch (JsonProcessingException ex) {
      log.warn("JSON parse/ mapping exception occurred. ", ex);
    }

    actions.get(operation).accept(before, after);
  }
}
