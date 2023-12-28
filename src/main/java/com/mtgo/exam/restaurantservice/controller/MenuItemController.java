package com.mtgo.exam.restaurantservice.controller;

import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Slf4j
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping("/menuItem/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody MenuItemRequest menuItemRequest, @RequestParam String restaurantId){
        menuItemService.createMenuItem(menuItemRequest, restaurantId);
    }

    @GetMapping("/menuItem/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemResponse> getAllMenuItemsInCurrency(@RequestParam(required = false) String currency){
        return menuItemService.getAllMenuItemsInCurrency(currency);
    }

    /*@GetMapping("/menuItem/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemResponse> getAllMenuItems(){
        return menuItemService.getAllMenuItems();
    }*/

    @GetMapping("menuItem/byRestaurant")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemResponse> getMenuItemsByRestaurantId(@RequestParam String restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }
}
