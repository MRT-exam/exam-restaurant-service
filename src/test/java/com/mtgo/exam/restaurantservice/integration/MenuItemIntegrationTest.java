package com.mtgo.exam.restaurantservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemDto;
import com.mtgo.exam.restaurantservice.model.MenuItem;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respository.MenuItemRepository;
import com.mtgo.exam.restaurantservice.respository.IRestaurantRepository;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
    private IRestaurantRepository IRestaurantRepository;

    @Autowired
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository1;
    @InjectMocks
    private MenuItemService menuItemService1;

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
        IRestaurantRepository.save(testRestaurant);
        MenuItemRequest menuItemRequest = MenuItemRequest.builder()
                .name("Test Item")
                .description("Test Description")
                .price(BigDecimal.valueOf(9.99))
                .id("testRestaurantId")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/restaurants/menuItem/create")
                        .param("restaurantId", "testRestaurantId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItemRequest)))
                .andExpect(status().isCreated());
        List<MenuItemDto> menuItems = menuItemService.getMenuItemsByRestaurantId("testRestaurantId");
        assertEquals(1, menuItems.size());
        MenuItemDto menuItemDto = menuItems.get(0);
        assertNotNull(menuItemDto.getId());
        assertEquals("Test Item", menuItemDto.getName());
        assertEquals("Test Description", menuItemDto.getDescription());
        assertEquals(BigDecimal.valueOf(9.99), menuItemDto.getPrice());
        assertNotNull(menuItemDto.getRestaurant());
        assertEquals("testRestaurantId", menuItemDto.getRestaurant().getId());
        assertEquals("Test Restaurant", menuItemDto.getRestaurant().getName());
    }

    // Should return a list of MenuItemResponse objects when given a valid restaurantId
    @Test
    public void ReturnListOfMenuItemResponseObjects() {

        // Arrange
        String restaurantId = "6554c6178dc83b51ed4e7414";
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .id("1")
                .name("Item 1")
                .description("Description 1")
                .price(BigDecimal.valueOf(10.99))
                .restaurant(new Restaurant())
                .build());
        menuItems.add(MenuItem.builder()
                .id("2")
                .name("Item 2")
                .description("Description 2")
                .price(BigDecimal.valueOf(15.99))
                .restaurant(new Restaurant())
                .build());
        Mockito.when(menuItemRepository1.findByRestaurant(restaurantId)).thenReturn(menuItems);

        // Act
        List<MenuItemDto> result = menuItemService1.getMenuItemsByRestaurantId(restaurantId);

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("Item 1", result.get(0).getName());
        Assert.assertEquals("Description 1", result.get(0).getDescription());
        Assert.assertEquals(BigDecimal.valueOf(10.99), result.get(0).getPrice());
        Assert.assertNotNull(result.get(0).getRestaurant());
        Assert.assertEquals("2", result.get(1).getId());
        Assert.assertEquals("Item 2", result.get(1).getName());
        Assert.assertEquals("Description 2", result.get(1).getDescription());
        Assert.assertEquals(BigDecimal.valueOf(15.99), result.get(1).getPrice());
        Assert.assertNotNull(result.get(1).getRestaurant());
    }


    // Should return an empty list when no menu items are found for the given restaurantId
    @Test
    public void returnEmptyListWhenNoMenuItemsFound() {
        // Arrange
        String restaurantId = "invalidRestaurantId";

        // Act
        List<MenuItemDto> result = menuItemService1.getMenuItemsByRestaurantId(restaurantId);

        // Assert
        assertTrue(result.isEmpty());
    }


    // Should throw an exception when given an invalid restaurantId
    @Test
    public void throwExceptionWhenGivenInvalidRestaurantId() {
        // Arrange
        String restaurantId = "invalidRestaurantId";
        Mockito.when(menuItemRepository1.findByRestaurant(restaurantId)).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> menuItemService1.getMenuItemsByRestaurantId(restaurantId));
    }
}
