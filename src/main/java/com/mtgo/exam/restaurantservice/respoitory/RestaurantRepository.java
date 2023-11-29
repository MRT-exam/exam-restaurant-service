package com.mtgo.exam.restaurantservice.respoitory;

import com.mtgo.exam.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
