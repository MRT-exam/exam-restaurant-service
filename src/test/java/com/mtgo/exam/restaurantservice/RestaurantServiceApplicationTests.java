package com.mtgo.exam.restaurantservice;

import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.model.MenuItem;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.MenuItemRepository;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class RestaurantServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Mock
	private MenuItemRepository menuItemRepository;
	@InjectMocks
	private MenuItemService menuItemService;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@BeforeEach
	void setUp() {
		Restaurant testRestaurant = new Restaurant();
		testRestaurant.setId("6554c6178dc83b51ed4e7414");
		testRestaurant.setName("Test Restaurant");
		restaurantRepository.save(testRestaurant);
	}
	@Test
	void shouldCreateMenuItem() throws Exception {
		MenuItemRequest menuItemRequest = getMenuItemRequest();
		String menuItemRequestString = objectMapper.writeValueAsString(menuItemRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/menuItem")
						.param("restaurantId", "6554c6178dc83b51ed4e7414")
						.contentType(MediaType.APPLICATION_JSON)
						.content(menuItemRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, restaurantRepository.findAll().size());
	}

	private MenuItemRequest getMenuItemRequest() {
		return MenuItemRequest.builder()
				.name("trabilsaktor")
				.description("asdasd")
				.price(BigDecimal.valueOf(2222))
				.id("6554c6178dc83b51ed4e7414")
				.build();
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
		Mockito.when(menuItemRepository.findByRestaurant(restaurantId)).thenReturn(menuItems);

		// Act
		List<MenuItemResponse> result = menuItemService.getMenuItemsByRestaurantId(restaurantId);

		// Assert
		assertEquals(2, result.size());
		assertEquals("1", result.get(0).getId());
		assertEquals("Item 1", result.get(0).getName());
		assertEquals("Description 1", result.get(0).getDescription());
		assertEquals(BigDecimal.valueOf(10.99), result.get(0).getPrice());
		assertNotNull(result.get(0).getRestaurant());
		assertEquals("2", result.get(1).getId());
		assertEquals("Item 2", result.get(1).getName());
		assertEquals("Description 2", result.get(1).getDescription());
		assertEquals(BigDecimal.valueOf(15.99), result.get(1).getPrice());
		assertNotNull(result.get(1).getRestaurant());
	}


	// Should return an empty list when no menu items are found for the given restaurantId
	@Test
	public void returnEmptyListWhenNoMenuItemsFound() {
		// Arrange
		String restaurantId = "invalidRestaurantId";
		Mockito.when(menuItemRepository.findByRestaurant(restaurantId)).thenReturn(Collections.emptyList());

		// Act
		List<MenuItemResponse> result = menuItemService.getMenuItemsByRestaurantId(restaurantId);

		// Assert
		assertTrue(result.isEmpty());
	}


	// Should throw an exception when given an invalid restaurantId
	@Test
	public void throwExceptionWhenGivenInvalidRestaurantId() {
		// Arrange
		String restaurantId = "invalidRestaurantId";
		Mockito.when(menuItemRepository.findByRestaurant(restaurantId)).thenReturn(null);

		// Act and Assert
		assertThrows(RuntimeException.class, () -> menuItemService.getMenuItemsByRestaurantId(restaurantId));
	}


}
