package com.mtgo.exam.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String email;

}
