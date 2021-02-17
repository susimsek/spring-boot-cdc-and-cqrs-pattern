
package com.spring.denormalizer.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.denormalizer.event.enums.DebeziumEventPayloadOperation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@JsonIgnoreProperties
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebeziumEventPayload {

  String before;
  String after;
  String patch;
  String filter;
  Date date;
  DebeziumEventPayloadOperation operation;
}