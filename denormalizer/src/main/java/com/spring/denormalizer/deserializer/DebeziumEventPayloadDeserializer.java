package com.spring.denormalizer.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.denormalizer.event.DebeziumEventPayload;
import com.spring.denormalizer.event.enums.DebeziumEventPayloadOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class DebeziumEventPayloadDeserializer implements Deserializer {

    static ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public DebeziumEventPayload deserialize(String s, byte[] bytes) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var rootNode = objectMapper.readTree(bytes);

            JsonNode after = rootNode.path("after");
            JsonNode before = rootNode.path("before");
            JsonNode patch = rootNode.path("patch");
            JsonNode filter = rootNode.path("filter");
            JsonNode epochTime = rootNode.path("ts_ms");
            JsonNode operation = rootNode.path("op");

            return DebeziumEventPayload.builder()
                    .after(after.asText())
                    .before(before.asText())
                    .patch(patch.asText())
                    .filter(filter.asText())
                    .operation(DebeziumEventPayloadOperation.value(operation.asText()))
                    .build();
        } catch (IOException ex) {
            log.warn("JSON parse/ mapping exception occurred. ", ex);
            return new DebeziumEventPayload();
        }
    }

    @Override
    public void close() {
        log.debug("DebeziumEventPayloadDeserializer closed");
    }
}
