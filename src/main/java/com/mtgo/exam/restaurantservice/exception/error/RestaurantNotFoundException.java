package com.mtgo.exam.restaurantservice.exception.error;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
