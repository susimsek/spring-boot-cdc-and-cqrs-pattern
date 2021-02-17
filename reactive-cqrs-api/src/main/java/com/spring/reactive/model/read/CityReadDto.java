package com.spring.reactive.model.read;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityReadDto {

    String id;

    String name;

    int population;

    Date createdDate;

}
