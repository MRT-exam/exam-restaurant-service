package com.mtgo.exam.restaurantservice.controller;


import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController  {

    private final RestaurantService restaurantService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.createRestaurant(restaurantRequest);
    }

    @PostMapping("/menuItem/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        restaurantService.createMenuItem(menuItemRequest);
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurantDtos = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurantDtos, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<MenuItemDto>> getMenu(@PathVariable String restaurantId) {
        List<MenuItemDto> menuItemDtos = restaurantService.getMenuItemsByRestaurantId(restaurantId);
        return new ResponseEntity<>(menuItemDtos, HttpStatus.OK);
    }

    @PostMapping("/populate")
    @ResponseStatus(HttpStatus.CREATED)
    public void populateDatabaseFromCsv() {
        restaurantService.populateDatabaseFromCsv();
    }

}
