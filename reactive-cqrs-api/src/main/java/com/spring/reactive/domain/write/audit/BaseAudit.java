package com.spring.reactive.domain.write.audit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public abstract class BaseAudit implements Serializable {

    @Id
    String id;

    @CreatedDate
    Date createdDate;

    @LastModifiedDate
    Date updatedDate;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String updatedBy;

    @Version
    Long version;
}
