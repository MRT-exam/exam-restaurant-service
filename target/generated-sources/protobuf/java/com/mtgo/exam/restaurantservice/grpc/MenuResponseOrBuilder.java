// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Menu.proto

package com.mtgo.exam.restaurantservice.grpc;

public interface MenuResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.mtgo.exam.restaurantservice.grpc.MenuResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.mtgo.exam.restaurantservice.grpc.Item item = 1;</code>
   */
  java.util.List<com.mtgo.exam.restaurantservice.grpc.Item> 
      getItemList();
  /**
   * <code>repeated .com.mtgo.exam.restaurantservice.grpc.Item item = 1;</code>
   */
  com.mtgo.exam.restaurantservice.grpc.Item getItem(int index);
  /**
   * <code>repeated .com.mtgo.exam.restaurantservice.grpc.Item item = 1;</code>
   */
  int getItemCount();
  /**
   * <code>repeated .com.mtgo.exam.restaurantservice.grpc.Item item = 1;</code>
   */
  java.util.List<? extends com.mtgo.exam.restaurantservice.grpc.ItemOrBuilder> 
      getItemOrBuilderList();
  /**
   * <code>repeated .com.mtgo.exam.restaurantservice.grpc.Item item = 1;</code>
   */
  com.mtgo.exam.restaurantservice.grpc.ItemOrBuilder getItemOrBuilder(
      int index);
}
