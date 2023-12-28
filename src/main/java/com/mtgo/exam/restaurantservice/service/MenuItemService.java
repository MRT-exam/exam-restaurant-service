package com.mtgo.exam.restaurantservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.model.MenuItem;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.MenuItemRepository;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

@Service
@RequiredArgsConstructor //laver vores menuitemrespoity construtor når man compiler
@Slf4j // vores logging
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public void createMenuItem(MenuItemRequest menuItemRequest, String restaurantId){
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

    }

    public List<MenuItemResponse> getAllMenuItemsInCurrency(String currency){
        List<MenuItem> menuItems = menuItemRepository.findAll();
        if (currency != null){
            double rate = getCurrencyConvertion(currency);
            for (MenuItem item: menuItems) {
                item.setPrice(item.getPrice().multiply(new BigDecimal(rate)));
            }
        }
        return menuItems.stream().map(this::mapToMenuItemResponse).toList();
    }

    private double getCurrencyConvertion(String currency){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_0dFB9lxrvoPe1l392xGkRWZJFfxpnv3uCFNoYnwo&currencies=" + currency + "&base_currency=DKK"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            // Parse JSON string to JsonNode
            JsonNode jsonNode = objectMapper.readTree(response.body());

            // Extract value
            double exchangeRate = jsonNode.get("data").get(currency).asDouble();
            return exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
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
