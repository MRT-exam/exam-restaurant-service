//package com.mtgo.exam.restaurantservice.grpcService;
//
//import com.mtgo.exam.restaurantservice.dto.MenuItemResponse;
//import com.mtgo.exam.restaurantservice.grpc.MenuRequest;
//import com.mtgo.exam.restaurantservice.grpc.MenuResponse;
//import com.mtgo.exam.restaurantservice.grpc.Item;
//import com.mtgo.exam.restaurantservice.grpc.MenuServiceGrpc;
//import io.grpc.stub.StreamObserver;
//import lombok.RequiredArgsConstructor;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//import com.mtgo.exam.restaurantservice.service.MenuItemService;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@GrpcService
//@EnableAutoConfiguration
//public class MenuServiceImpl extends MenuServiceGrpc.MenuServiceImplBase {
//    private final MenuItemService menuItemService;
//    public void menu(MenuRequest request, StreamObserver<MenuResponse> responseObserver) {
//        String restaurantID = request.getRestaurantID();
//        List<MenuItemResponse> items = menuItemService.getMenuItemsByRestaurantId(restaurantID);
//        List<Item> responseItems = new ArrayList<>();
//        for (MenuItemResponse item: items) {
//            responseItems.add(Item.newBuilder()
//                    .setItemID(item.getId())
//                    .setName(item.getName())
//                    .build());
//        }
//        MenuResponse res = MenuResponse.newBuilder()
//                .addAllItem(responseItems)
//                .build();
//        responseObserver.onNext(res);
//        responseObserver.onCompleted();
//    }
//}
