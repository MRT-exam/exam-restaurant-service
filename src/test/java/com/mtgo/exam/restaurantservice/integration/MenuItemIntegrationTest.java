package com.mtgo.exam.restaurantservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.MenuItemRepository;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("integration")
class MenuItemIntegrationTest {
//
//    Here we are using a real MongoDB container to spin up a real MongoDB instance for our tests.
//    The test interacts with the MongoDB database through the menuItemRepository and restaurantRepository to save and retrieve data.
//
//    the test also interacts with the MenuItemService which is a service layer component responsible for all the business logic.
//    It makes a request to create a menu item through the MockMvc which then is simulating an HTTP request, which goes through the entire Spring MVC stack and hits our controller endpoint.
//
//    The test performs a then simulate a HTTP request by using MockMvc to hit the /api/menuItem endpoint
//
//    then lastly the test integrates the MenuItemRequest, MenuItemResponse, MenuItem, Restaurant, and other components to create, save, and retrieve menu items.

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemService menuItemService;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void createMenuItemAndRetrieveFromDatabase() throws Exception {
        // creating a test restaurant
        Restaurant testRestaurant = new Restaurant();
        testRestaurant.setId("testRestaurantId");
        testRestaurant.setName("Test Restaurant");
        restaurantRepository.save(testRestaurant);

        // creating the  menu item request
        MenuItemRequest menuItemRequest = MenuItemRequest.builder()
                .name("Test Item")
                .description("Test Description")
                .price(BigDecimal.valueOf(9.99))
                .id("testRestaurantId")  // Use the test restaurant's ID
                .build();

        // making the request to create a menu item
        mockMvc.perform(MockMvcRequestBuilders.post("/api/menuItem")
                        .param("restaurantId", "testRestaurantId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isCreated());

        // getting menu items from the database
        List<MenuItemResponse> menuItems = menuItemService.getMenuItemsByRestaurantId("testRestaurantId");

        // assert that the menu item was created and retrieved
        assertEquals(1, menuItems.size());
        MenuItemResponse menuItemResponse = menuItems.get(0);
        assertNotNull(menuItemResponse.getId());
        assertEquals("Test Item", menuItemResponse.getName());
        assertEquals("Test Description", menuItemResponse.getDescription());
        assertEquals(BigDecimal.valueOf(9.99), menuItemResponse.getPrice());
        assertNotNull(menuItemResponse.getRestaurant());
        assertEquals("testRestaurantId", menuItemResponse.getRestaurant().getId());
        assertEquals("Test Restaurant", menuItemResponse.getRestaurant().getName());
    }
}
