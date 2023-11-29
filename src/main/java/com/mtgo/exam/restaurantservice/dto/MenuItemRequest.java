package com.mtgo.exam.restaurantservice.dto;

import com.MTGO.restaurantservice.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    //private Restaurant restaurant;
    private String id;
}
