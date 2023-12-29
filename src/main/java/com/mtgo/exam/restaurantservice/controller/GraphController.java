package com.mtgo.exam.restaurantservice.controller;

import com.mtgo.exam.restaurantservice.dto.RestaurantResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.mtgo.exam.restaurantservice.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class GraphController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    //private final RestaurantRepository restaurantRepository;
    //private final RestaurantService restaurantService;

    @Autowired
    public GraphController(RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    @QueryMapping
    Iterable<Restaurant> restaurants () {
        return restaurantRepository.findAll();
    }

    public List<RestaurantResponse> getAllMenuRestaurants(){
        return restaurantService.getAllRestaurants();
    }


    @QueryMapping
    public Optional<RestaurantResponse> restaurantById(@Argument String id) {
        log.info("Quering the restaurant data with the id {}", id);
        return restaurantService.getRestaurantById(id);
    }
}
