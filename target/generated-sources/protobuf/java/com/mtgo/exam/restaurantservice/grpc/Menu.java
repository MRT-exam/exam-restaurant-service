// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Menu.proto

package com.mtgo.exam.restaurantservice.grpc;

public final class Menu {
  private Menu() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_mtgo_exam_restaurantservice_grpc_MenuRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_mtgo_exam_restaurantservice_grpc_MenuResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_mtgo_exam_restaurantservice_grpc_Item_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_mtgo_exam_restaurantservice_grpc_Item_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nMenu.proto\022$com.mtgo.exam.restaurantse" +
      "rvice.grpc\"#\n\013MenuRequest\022\024\n\014restaurantI" +
      "D\030\001 \001(\t\"H\n\014MenuResponse\0228\n\004item\030\001 \003(\0132*." +
      "com.mtgo.exam.restaurantservice.grpc.Ite" +
      "m\"$\n\004Item\022\016\n\006itemID\030\001 \001(\t\022\014\n\004name\030\002 \001(\t2" +
      "~\n\013MenuService\022o\n\004Menu\0221.com.mtgo.exam.r" +
      "estaurantservice.grpc.MenuRequest\0322.com." +
      "mtgo.exam.restaurantservice.grpc.MenuRes" +
      "ponse\"\000B(\n$com.mtgo.exam.restaurantservi" +
      "ce.grpcP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_mtgo_exam_restaurantservice_grpc_MenuRequest_descriptor,
        new java.lang.String[] { "RestaurantID", });
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_mtgo_exam_restaurantservice_grpc_MenuResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_mtgo_exam_restaurantservice_grpc_MenuResponse_descriptor,
        new java.lang.String[] { "Item", });
    internal_static_com_mtgo_exam_restaurantservice_grpc_Item_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_mtgo_exam_restaurantservice_grpc_Item_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_mtgo_exam_restaurantservice_grpc_Item_descriptor,
        new java.lang.String[] { "ItemID", "Name", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}