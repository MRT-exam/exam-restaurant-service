package com.mtgo.exam.restaurantservice.respoitory;

import com.mtgo.exam.restaurantservice.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
}
