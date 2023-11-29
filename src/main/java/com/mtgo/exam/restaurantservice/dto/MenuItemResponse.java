package com.mtgo.exam.restaurantservice.dto;


import com.mtgo.exam.restaurantservice.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Restaurant restaurant;
}
