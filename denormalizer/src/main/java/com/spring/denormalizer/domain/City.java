package com.spring.denormalizer.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "cities")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    String id;

    Date createdDate;

    Date updatedDate;

    String createdBy;

    String updatedBy;

    Long version;

    String name;

    int population;
}
