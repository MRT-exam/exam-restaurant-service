package com.mtgo.exam.restaurantservice;

import com.mtgo.exam.restaurantservice.dto.MenuItemRequest;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
}
