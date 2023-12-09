package com.mtgo.exam.restaurantservice.controller;


import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantResponse;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import com.mtgo.exam.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    @PostMapping("/fromCsv")
    @ResponseStatus(HttpStatus.CREATED)
    public void populateDatabaseFromCsv() {
        restaurantService.populateDatabaseFromCsv();
    }

    private final RestaurantService restaurantService;
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.createRestaurant(restaurantRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantResponse> getAllMenuRestaurants(){
        return restaurantService.getAllRestaurants();
    }
}
