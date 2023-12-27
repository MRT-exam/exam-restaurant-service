package com.mtgo.exam.restaurantservice.service;

import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.model.MenuItem;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.MenuItemRepository;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor //laver vores menuitemrespoity construtor når man compiler
@Slf4j // vores logging
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public void createMenuItem(MenuItemRequest menuItemRequest, String restaurantId){
        try {
            log.info("Creating menu item for restaurant with ID: {}", restaurantId);
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
            MenuItem menuItem = MenuItem.builder()
                    .name(menuItemRequest.getName())
                    .description(menuItemRequest.getDescription())
                    .price(menuItemRequest.getPrice())
                    .restaurant(restaurant)
                    .build();
            menuItemRepository.save(menuItem);
            log.info("Menu item {} is saved", menuItem.getId(), restaurantId);
        }catch (Exception e) {
            log.error("Error creating menu item: {}", menuItemRequest, e);
            throw new RuntimeException("Error creating menu item", e);
        }
    }

    public List<MenuItemResponse> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return menuItems.stream().map(this::mapToMenuItemResponse).toList();
    }

    public List<MenuItemResponse> getMenuItemsByRestaurantId(String restaurantId) {
        log.info("Getting menu items for restaurant with ID: {}", restaurantId);

        List<MenuItem> menuItems = menuItemRepository.findByRestaurant(restaurantId);

        return menuItems.stream().map(this::mapToMenuItemResponse).toList();
    }

    private MenuItemResponse mapToMenuItemResponse(MenuItem menuItem){
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .restaurant(menuItem.getRestaurant())
                .build();
    }
}
