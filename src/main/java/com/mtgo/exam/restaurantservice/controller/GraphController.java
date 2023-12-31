package com.mtgo.exam.restaurantservice.controller;

import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respository.IRestaurantRepository;
import com.mtgo.exam.restaurantservice.service.IRestaurantService;
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

    private final IRestaurantRepository IRestaurantRepository;
    private final IRestaurantService restaurantService;

    @Autowired
    public GraphController(IRestaurantRepository IRestaurantRepository, RestaurantService restaurantService) {
        this.IRestaurantRepository = IRestaurantRepository;
        this.restaurantService = restaurantService;
    }

    @QueryMapping
    public List<RestaurantDto> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @QueryMapping
    public List<MenuItemDto> getMenuItemsByRestaurantId(@Argument String restaurantId) {
        return restaurantService.getMenuItemsByRestaurantId(restaurantId);
    }

    @QueryMapping
    public RestaurantDto getRestaurantById(@Argument String id) {
        return restaurantService.getRestaurantById(id);
    }
}
