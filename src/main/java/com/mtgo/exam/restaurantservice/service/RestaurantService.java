package com.mtgo.exam.restaurantservice.service;

import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.dto.RestaurantRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //laver vores menuitemrespoity construtor når man compiler
@Slf4j // vores logging
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemService menuItemService;

    public void createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .email(restaurantRequest.getEmail())
                .build();
        restaurantRepository.save(restaurant);
        log.info("Restaurant {} is saved", restaurant.getId());
    }

    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(this::mapToRestaurantResponse).toList();
    }

    private RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .email(restaurant.getEmail())
                .build();
    }

//    public void populateDatabaseFromCsv() {
//        try {
//            Path csvFilePath = Paths.get("src/main/resources/data/restaurant.csv");
//            List<RestaurantRequest> restaurantRequests = readRestaurantCsv(csvFilePath);
//
//            for (RestaurantRequest request : restaurantRequests) {
//                createRestaurant(request);
//            }
//
//            log.info("Database populated from CSV successfully.");
//
//        } catch (IOException e) {
//            log.error("Error reading CSV file: {}", e.getMessage());
//        }
//    }

//    private List<RestaurantRequest> readRestaurantCsv(Path filePath) throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
//            return reader.lines()
//                    .skip(1) // Skip header line if it exists
//                    .map(line -> {
//                        String[] values = line.split(",");
//                        return new RestaurantRequest(values[0], values[1]);
//                    })
//                    .toList();
//        }
//    }
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
