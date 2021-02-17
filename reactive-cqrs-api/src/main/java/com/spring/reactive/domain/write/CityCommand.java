package com.spring.reactive.domain.write;


import com.spring.reactive.domain.write.audit.BaseAudit;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cities")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityCommand extends BaseAudit {

    String name;

    int population;
}
