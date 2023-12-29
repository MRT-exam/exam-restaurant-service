package com.mtgo.exam.restaurantservice.respository;

import com.mtgo.exam.restaurantservice.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
     List<MenuItem> findByRestaurant(String restaurantId);
}
