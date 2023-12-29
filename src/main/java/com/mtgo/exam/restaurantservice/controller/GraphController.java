package com.mtgo.exam.restaurantservice.controller;

import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.dto.RestaurantResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
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

    private final MenuItemService menuItemService;

    //private final RestaurantRepository restaurantRepository;
    //private final RestaurantService restaurantService;

    @Autowired
    public GraphController(RestaurantRepository restaurantRepository, RestaurantService restaurantService, MenuItemService menuItemService ) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    @QueryMapping
    Iterable<Restaurant> restaurants () {
        return restaurantRepository.findAll();
    }

    public List<RestaurantResponse> getAllMenuRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @QueryMapping
    public List<MenuItemResponse> menuItemsByRestaurantId(@Argument String restaurantId) {
        log.info("Querying menu items for restaurant with id {}", restaurantId);
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }


    @QueryMapping
    public Optional<RestaurantResponse> restaurantById(@Argument String id) {
        log.info("Quering the restaurant data with the id {}", id);
        return restaurantService.getRestaurantById(id);
    }
}
