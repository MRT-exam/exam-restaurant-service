package com.mtgo.exam.restaurantservice.respository;

import com.mtgo.exam.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {
}
