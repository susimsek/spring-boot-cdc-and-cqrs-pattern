package com.spring.denormalizer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.denormalizer.model.audit.BaseAudit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityWriteDto extends BaseAudit {

    String id;

    String name;

    int population;

    @JsonProperty("_id")
    private void setId(Map<String, String> idMap) {
        id = idMap.get("$oid");
    }
}

