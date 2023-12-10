package com.mtgo.exam.restaurantservice.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: Menu.proto")
public final class MenuServiceGrpc {

  private MenuServiceGrpc() {}

  public static final String SERVICE_NAME = "com.mtgo.exam.restaurantservice.grpc.MenuService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mtgo.exam.restaurantservice.grpc.MenuRequest,
      com.mtgo.exam.restaurantservice.grpc.MenuResponse> getMenuMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Menu",
      requestType = com.mtgo.exam.restaurantservice.grpc.MenuRequest.class,
      responseType = com.mtgo.exam.restaurantservice.grpc.MenuResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.mtgo.exam.restaurantservice.grpc.MenuRequest,
      com.mtgo.exam.restaurantservice.grpc.MenuResponse> getMenuMethod() {
    io.grpc.MethodDescriptor<com.mtgo.exam.restaurantservice.grpc.MenuRequest, com.mtgo.exam.restaurantservice.grpc.MenuResponse> getMenuMethod;
    if ((getMenuMethod = MenuServiceGrpc.getMenuMethod) == null) {
      synchronized (MenuServiceGrpc.class) {
        if ((getMenuMethod = MenuServiceGrpc.getMenuMethod) == null) {
          MenuServiceGrpc.getMenuMethod = getMenuMethod =
              io.grpc.MethodDescriptor.<com.mtgo.exam.restaurantservice.grpc.MenuRequest, com.mtgo.exam.restaurantservice.grpc.MenuResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Menu"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mtgo.exam.restaurantservice.grpc.MenuRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mtgo.exam.restaurantservice.grpc.MenuResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MenuServiceMethodDescriptorSupplier("Menu"))
              .build();
        }
      }
    }
    return getMenuMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MenuServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MenuServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MenuServiceStub>() {
        @java.lang.Override
        public MenuServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MenuServiceStub(channel, callOptions);
        }
      };
    return MenuServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MenuServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MenuServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MenuServiceBlockingStub>() {
        @java.lang.Override
        public MenuServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MenuServiceBlockingStub(channel, callOptions);
        }
      };
    return MenuServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MenuServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MenuServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MenuServiceFutureStub>() {
        @java.lang.Override
        public MenuServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MenuServiceFutureStub(channel, callOptions);
        }
      };
    return MenuServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MenuServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void menu(com.mtgo.exam.restaurantservice.grpc.MenuRequest request,
        io.grpc.stub.StreamObserver<com.mtgo.exam.restaurantservice.grpc.MenuResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMenuMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMenuMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.mtgo.exam.restaurantservice.grpc.MenuRequest,
                com.mtgo.exam.restaurantservice.grpc.MenuResponse>(
                  this, METHODID_MENU)))
          .build();
    }
  }

  /**
   */
  public static final class MenuServiceStub extends io.grpc.stub.AbstractAsyncStub<MenuServiceStub> {
    private MenuServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MenuServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MenuServiceStub(channel, callOptions);
    }

    /**
     */
    public void menu(com.mtgo.exam.restaurantservice.grpc.MenuRequest request,
        io.grpc.stub.StreamObserver<com.mtgo.exam.restaurantservice.grpc.MenuResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMenuMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MenuServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MenuServiceBlockingStub> {
    private MenuServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MenuServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MenuServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.mtgo.exam.restaurantservice.grpc.MenuResponse menu(com.mtgo.exam.restaurantservice.grpc.MenuRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMenuMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MenuServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MenuServiceFutureStub> {
    private MenuServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MenuServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MenuServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mtgo.exam.restaurantservice.grpc.MenuResponse> menu(
        com.mtgo.exam.restaurantservice.grpc.MenuRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMenuMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MENU = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MenuServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MenuServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MENU:
          serviceImpl.menu((com.mtgo.exam.restaurantservice.grpc.MenuRequest) request,
              (io.grpc.stub.StreamObserver<com.mtgo.exam.restaurantservice.grpc.MenuResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MenuServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MenuServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mtgo.exam.restaurantservice.grpc.Menu.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MenuService");
    }
  }

  private static final class MenuServiceFileDescriptorSupplier
      extends MenuServiceBaseDescriptorSupplier {
    MenuServiceFileDescriptorSupplier() {}
  }

  private static final class MenuServiceMethodDescriptorSupplier
      extends MenuServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MenuServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MenuServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MenuServiceFileDescriptorSupplier())
              .addMethod(getMenuMethod())
              .build();
        }
      }
    }
    return result;
  }
}
