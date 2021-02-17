package com.spring.denormalizer.model.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public abstract class BaseAudit {

    Date createdDate;

    Date updatedDate;

    String createdBy;

    String updatedBy;

    Long version;

    @JsonProperty("createdDate")
    private void setCreatedDate(Map<String, Date> createdDateMap) {
        createdDate = createdDateMap.get("$date");
    }

    @JsonProperty("updatedDate")
    private void setUpdatedDate(Map<String, Date> updatedDateMap) {
        updatedDate = updatedDateMap.get("$date");
    }

    @JsonProperty("version")
    private void setVersion(Map<String, Long> versionMap) {
        version = versionMap.get("$numberLong");
    }
}
