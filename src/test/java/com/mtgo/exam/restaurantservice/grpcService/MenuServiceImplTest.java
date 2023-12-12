package com.mtgo.exam.restaurantservice.grpcService;

import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
import com.mtgo.exam.restaurantservice.grpc.MenuRequest;
import com.mtgo.exam.restaurantservice.grpc.MenuResponse;
import com.mtgo.exam.restaurantservice.model.Restaurant;
import com.mtgo.exam.restaurantservice.respoitory.MenuItemRepository;
import com.mtgo.exam.restaurantservice.service.MenuItemService;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MenuServiceImplTest {


//
//    Logic performed by the menu grpc server is tested here

    @Mock
    private MenuItemRepository menuItemRepository1;

    @Mock
    private MenuItemService menuItemService1;

    @InjectMocks
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuServiceImpl menuService;

    @Test
    void receiveZeroMenuItems() throws Exception {
        String restaurantId = "id";

        List<MenuItemResponse> items = new ArrayList<>();

        Mockito.when(menuItemService1.getMenuItemsByRestaurantId(restaurantId))
                .thenReturn(items);

        MenuRequest menuRequest = MenuRequest.newBuilder()
                .setRestaurantID(restaurantId)
                .build();

        StreamRecorder<MenuResponse> responseObserver = StreamRecorder.create();

        menuService.menu(menuRequest, responseObserver);
        //menuItemService.getMenuItemsByRestaurantId(restaurantId);

        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<MenuResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        MenuResponse response = results.get(0);
        assertEquals(0, response.getItemCount());
    }

    @Test
    void receiveMultipleMenuItems() throws Exception {
        String restaurantId = "id";

        List<MenuItemResponse> items = new ArrayList<>();
        items.add(new MenuItemResponse("id1", "testItem1", "discription of item 1", new BigDecimal(10) , new Restaurant()));
        items.add(new MenuItemResponse("id2", "testItem2", "discription of item 1", new BigDecimal(10) , new Restaurant()));

        Mockito.when(menuItemService1.getMenuItemsByRestaurantId(restaurantId))
                .thenReturn(items);

        MenuRequest menuRequest = MenuRequest.newBuilder()
                .setRestaurantID(restaurantId)
                .build();

        StreamRecorder<MenuResponse> responseObserver = StreamRecorder.create();

        menuService.menu(menuRequest, responseObserver);
        //menuItemService.getMenuItemsByRestaurantId(restaurantId);

        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<MenuResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        MenuResponse response = results.get(0);
        assertEquals(2, response.getItemCount());
    }

    @Test
    void parameterMappingMenuItemToItem() throws Exception {
        String restaurantId = "id";

        List<MenuItemResponse> items = new ArrayList<>();
        items.add(new MenuItemResponse("id1", "testItem1", "discription of item 1", new BigDecimal(10) , new Restaurant()));

        Mockito.when(menuItemService1.getMenuItemsByRestaurantId(restaurantId))
                .thenReturn(items);

        MenuRequest menuRequest = MenuRequest.newBuilder()
                .setRestaurantID(restaurantId)
                .build();

        StreamRecorder<MenuResponse> responseObserver = StreamRecorder.create();

        menuService.menu(menuRequest, responseObserver);
        //menuItemService.getMenuItemsByRestaurantId(restaurantId);

        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<MenuResponse> results = responseObserver.getValues();
        MenuResponse response = results.get(0);
        assertEquals("id1", response.getItem(0).getItemID());
    }

}
