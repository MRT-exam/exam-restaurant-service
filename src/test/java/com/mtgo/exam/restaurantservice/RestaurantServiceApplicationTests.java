package com.mtgo.exam.restaurantservice;

import com.mtgo.exam.restaurantservice.controller.RestaurantController;
import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.dto.RestaurantDto;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respository.IRestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
	private IRestaurantRepository IRestaurantRepository;
	@Autowired
	private RestaurantController restaurantController;
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@BeforeEach
	void setUp() {
		Restaurant testRestaurant = new Restaurant();
		testRestaurant.setId("6554c6178dc83b51ed4e7414");
		testRestaurant.setName("Test Restaurant");
		IRestaurantRepository.save(testRestaurant);
	}
	/*
	@Test
	void shouldCreateMenuItem() throws Exception {
		MenuItemRequest menuItemRequest = getMenuItemRequest();
		String menuItemRequestString = objectMapper.writeValueAsString(menuItemRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/restaurants/menuItem/create")
						.param("restaurantId", "6554c6178dc83b51ed4e7414")
						.contentType(MediaType.APPLICATION_JSON)
						.content(menuItemRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, IRestaurantRepository.findAll().size());
	}

	 */

	private MenuItemRequest getMenuItemRequest() {
		return MenuItemRequest.builder()
				.name("trabilsaktor")
				.description("asdasd")
				.price(BigDecimal.valueOf(2222))
				.restaurantId("6554c6178dc83b51ed4e7414")
				.build();
	}
	@Test
	public void test_instantiation_with_all_fields_populated() {
		String id = "123";
		String name = "Restaurant";
		String email = "restaurant@example.com";
		RestaurantDto response = new RestaurantDto(id, name, email);
		assertEquals(id, response.getId());
		assertEquals(name, response.getName());
		assertEquals(email, response.getEmail());
	}

	@Test
	public void test_successful_csv_read_and_create_restaurants() {
		restaurantController.populateDatabaseFromCsv();
		ResponseEntity<List<RestaurantDto>> createdRestaurants = restaurantController.getAllRestaurants();

		assertNotNull(createdRestaurants);
		assertFalse(createdRestaurants.getBody().isEmpty());

	}


}
