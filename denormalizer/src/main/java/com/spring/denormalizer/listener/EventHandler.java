
package com.spring.denormalizer.listener;

import com.spring.denormalizer.event.DebeziumEventPayload;

public interface EventHandler {

  void process(DebeziumEventPayload event);

}