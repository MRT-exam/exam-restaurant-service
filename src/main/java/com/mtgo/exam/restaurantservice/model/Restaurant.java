package com.mtgo.exam.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    @DBRef
    private List<MenuItem> menuItems;
}
