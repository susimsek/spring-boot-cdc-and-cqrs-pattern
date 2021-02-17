
package com.spring.denormalizer.event.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public enum DebeziumEventPayloadOperation {
  CREATE, UPDATE, DELETE;

  public static DebeziumEventPayloadOperation value(String name) {
    switch (name) {
      case "c":
      case "r":
        return CREATE;
      case "u":
        return UPDATE;
      case "d":
        return DELETE;
      default:
        throw new IllegalArgumentException("There is no match for the specified value");
    }
  }
}
