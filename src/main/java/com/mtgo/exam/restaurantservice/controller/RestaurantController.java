package com.mtgo.exam.restaurantservice.controller;


import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.mtgo.exam.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController  {

    private final RestaurantRepository restaurantRepository;

    @PostMapping("/populate")
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

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<RestaurantResponse> getAllMenuRestaurants(){
        return restaurantService.getAllRestaurants();
    }

//    @GetMapping("/{restaurantId}")
//    @ResponseStatus(HttpStatus.OK)
//    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId) {
//        return restaurantService.getRestaurantById(restaurantId)
//                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
//    }
}
