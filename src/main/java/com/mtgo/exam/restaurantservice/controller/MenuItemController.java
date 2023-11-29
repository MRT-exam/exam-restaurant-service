package com.mtgo.exam.restaurantservice.controller;

import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menuItem")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody MenuItemRequest menuItemRequest, @RequestParam String restaurantId){
        menuItemService.createMenuItem(menuItemRequest, restaurantId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemResponse> getAllMenuItems(){
        return menuItemService.getAllMenuItems();
    }
}
