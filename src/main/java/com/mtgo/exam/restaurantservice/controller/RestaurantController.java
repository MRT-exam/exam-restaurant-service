package com.MTGO.restaurantservice.controller;

import com.MTGO.restaurantservice.dto.MenuItemResponse;
import com.MTGO.restaurantservice.dto.RestaurantRequest;
import com.MTGO.restaurantservice.dto.RestaurantResponse;
import com.MTGO.restaurantservice.service.MenuItemService;
import com.MTGO.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/populate")
@RequiredArgsConstructor
public class RestaurantController {

    @PostMapping("/fromCsv")
    @ResponseStatus(HttpStatus.CREATED)
    public void populateDatabaseFromCsv() {
        restaurantService.populateDatabaseFromCsv();
    }

    private final RestaurantService restaurantService;
    @PostMapping
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
