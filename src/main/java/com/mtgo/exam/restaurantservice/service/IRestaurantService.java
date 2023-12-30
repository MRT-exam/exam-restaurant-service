package com.mtgo.exam.restaurantservice.service;

import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    public RestaurantDto createRestaurant(RestaurantRequest request);

    public void createMenuItem(MenuItemRequest request);

    public List<RestaurantDto> getAllRestaurants();

    public List<MenuItemDto> getMenuItemsByRestaurantId(String restaurantId);

    public RestaurantDto getRestaurantById(String id);

    public List<MenuItemDto> getMenuItemsConvertedCurrency(String restaurantId, String currency);
}
