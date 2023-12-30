package com.mtgo.exam.restaurantservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.exception.error.RestaurantNotFoundException;
import com.mtgo.exam.restaurantservice.model.MenuItem;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService implements IRestaurantService{

    private final IRestaurantRepository IRestaurantRepository;

    public RestaurantDto createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .email(restaurantRequest.getEmail())
                .menuItems(new ArrayList<>())
                .build();
        Restaurant savedRestaurant = IRestaurantRepository.save(restaurant);
        return this.mapToRestaurantDto(savedRestaurant);
    }
    @Override
    public void createMenuItem(MenuItemRequest menuItemRequest) {
        log.info("Creating menu item for restaurant with ID: {}", menuItemRequest.getRestaurantId());
        Restaurant restaurant = IRestaurantRepository.findById(menuItemRequest.getRestaurantId())
                    .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with id " + menuItemRequest.getRestaurantId() + " not found"));

        MenuItem menuItem = MenuItem.builder()
                    .name(menuItemRequest.getName())
                    .description(menuItemRequest.getDescription())
                    .price(menuItemRequest.getPrice())
                    .restaurant(restaurant)
                    .build();
        if (restaurant.getMenuItems() == null) {
            List<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(menuItem);
            restaurant.setMenuItems(menuItems);
        } else {
            restaurant.getMenuItems().add(menuItem);
        }
        IRestaurantRepository.save(restaurant);
    }
    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = IRestaurantRepository.findAll();
        if (restaurants.isEmpty()) {
            throw new RestaurantNotFoundException("No Restaurants in Database");
        }
        return restaurants.stream().map(this::mapToRestaurantDto).toList();
    }
    @Override
    public List<MenuItemDto> getMenuItemsConvertedCurrency(String restaurantId, String currency) {
        Restaurant restaurant = IRestaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with id " + restaurantId + "not found"));
        List<MenuItemDto> menuItemDtos = restaurant.getMenuItems()
                .stream()
                .map(this::mapToMenuItemDto)
                .toList();
        if (currency != null) {
            double rate = getCurrencyConvertion(currency);
            for (MenuItemDto menuItemDto : menuItemDtos) {
                menuItemDto.setPrice(menuItemDto.getPrice().multiply(new BigDecimal(rate).setScale(2, RoundingMode.CEILING)));
            }
        }
        return menuItemDtos;
    }

    @Override
    public List<MenuItemDto> getMenuItemsByRestaurantId(String restaurantId) {
        Restaurant restaurant = IRestaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with id " + restaurantId + "not found"));

        List<MenuItemDto> menuItemDtos = restaurant.getMenuItems()
                .stream()
                .map(this::mapToMenuItemDto)
                .toList();

        return menuItemDtos;
    }

    @Override
    public RestaurantDto getRestaurantById(String id) {
        Restaurant restaurant = IRestaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with id" + id + " not found"));
        return mapToRestaurantDto(restaurant);
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


    private RestaurantDto mapToRestaurantDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .email(restaurant.getEmail())
                .build();
    }
    private MenuItemDto mapToMenuItemDto(MenuItem menuItem){
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .build();
    }

    private List<RestaurantRequest> readRestaurantCsv(String filePath) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath))))) {
        return reader.lines()
                .skip(1)
                .map(line -> {
                    String[] values = line.split(",");
                    return new RestaurantRequest(values[0], values[1]);
                })
                .toList();
    }
}

    public void populateDatabaseFromCsv() {
        try {
            List<RestaurantRequest> restaurantRequests = readRestaurantCsv("data/restaurant.csv");

            for (RestaurantRequest request : restaurantRequests) {
                createRestaurant(request);
            }

            log.info("Database populated from CSV successfully.");

        } catch (IOException e) {
            log.error("Error reading ny fel CSV file: {}", e.getMessage());
        }
    }
}
