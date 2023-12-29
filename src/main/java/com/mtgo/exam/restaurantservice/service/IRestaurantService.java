package com.mtgo.exam.restaurantservice.service;

import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;

import java.util.List;

public interface IRestaurantService {

    public RestaurantDto createRestaurant(RestaurantRequest request);

    public List<RestaurantDto> getAllRestaurants();

    public List<MenuItemDto> getMenuItemsByRestaurantId(String restaurantId);
}
