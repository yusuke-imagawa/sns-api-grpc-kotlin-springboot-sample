package com.talking.grpc.push_notify;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: push_notify.proto")
public final class PushNotifyServiceGrpc {

  private PushNotifyServiceGrpc() {}

  public static final String SERVICE_NAME = "push_notify.PushNotifyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.push_notify.SavePushTokenIosRequest,
      com.google.protobuf.Empty> getSavePushTokenIosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SavePushTokenIos",
      requestType = com.talking.grpc.push_notify.SavePushTokenIosRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.push_notify.SavePushTokenIosRequest,
      com.google.protobuf.Empty> getSavePushTokenIosMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.push_notify.SavePushTokenIosRequest, com.google.protobuf.Empty> getSavePushTokenIosMethod;
    if ((getSavePushTokenIosMethod = PushNotifyServiceGrpc.getSavePushTokenIosMethod) == null) {
      synchronized (PushNotifyServiceGrpc.class) {
        if ((getSavePushTokenIosMethod = PushNotifyServiceGrpc.getSavePushTokenIosMethod) == null) {
          PushNotifyServiceGrpc.getSavePushTokenIosMethod = getSavePushTokenIosMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.push_notify.SavePushTokenIosRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SavePushTokenIos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.push_notify.SavePushTokenIosRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PushNotifyServiceMethodDescriptorSupplier("SavePushTokenIos"))
              .build();
        }
      }
    }
    return getSavePushTokenIosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.push_notify.UpdateNotifyRequest,
      com.google.protobuf.Empty> getUpdateNotifyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateNotify",
      requestType = com.talking.grpc.push_notify.UpdateNotifyRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.push_notify.UpdateNotifyRequest,
      com.google.protobuf.Empty> getUpdateNotifyMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.push_notify.UpdateNotifyRequest, com.google.protobuf.Empty> getUpdateNotifyMethod;
    if ((getUpdateNotifyMethod = PushNotifyServiceGrpc.getUpdateNotifyMethod) == null) {
      synchronized (PushNotifyServiceGrpc.class) {
        if ((getUpdateNotifyMethod = PushNotifyServiceGrpc.getUpdateNotifyMethod) == null) {
          PushNotifyServiceGrpc.getUpdateNotifyMethod = getUpdateNotifyMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.push_notify.UpdateNotifyRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateNotify"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.push_notify.UpdateNotifyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new PushNotifyServiceMethodDescriptorSupplier("UpdateNotify"))
              .build();
        }
      }
    }
    return getUpdateNotifyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PushNotifyServiceStub newStub(io.grpc.Channel channel) {
    return new PushNotifyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PushNotifyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PushNotifyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PushNotifyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PushNotifyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PushNotifyServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * プッシュ通知デバイストークン_iOS / 登録, 更新
     * </pre>
     */
    public void savePushTokenIos(com.talking.grpc.push_notify.SavePushTokenIosRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSavePushTokenIosMethod(), responseObserver);
    }

    /**
     * <pre>
     * プッシュ通知_ON/OFF更新
     * </pre>
     */
    public void updateNotify(com.talking.grpc.push_notify.UpdateNotifyRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateNotifyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSavePushTokenIosMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.push_notify.SavePushTokenIosRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_SAVE_PUSH_TOKEN_IOS)))
          .addMethod(
            getUpdateNotifyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.push_notify.UpdateNotifyRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_UPDATE_NOTIFY)))
          .build();
    }
  }

  /**
   */
  public static final class PushNotifyServiceStub extends io.grpc.stub.AbstractStub<PushNotifyServiceStub> {
    private PushNotifyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PushNotifyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PushNotifyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PushNotifyServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * プッシュ通知デバイストークン_iOS / 登録, 更新
     * </pre>
     */
    public void savePushTokenIos(com.talking.grpc.push_notify.SavePushTokenIosRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSavePushTokenIosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * プッシュ通知_ON/OFF更新
     * </pre>
     */
    public void updateNotify(com.talking.grpc.push_notify.UpdateNotifyRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateNotifyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PushNotifyServiceBlockingStub extends io.grpc.stub.AbstractStub<PushNotifyServiceBlockingStub> {
    private PushNotifyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PushNotifyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PushNotifyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PushNotifyServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * プッシュ通知デバイストークン_iOS / 登録, 更新
     * </pre>
     */
    public com.google.protobuf.Empty savePushTokenIos(com.talking.grpc.push_notify.SavePushTokenIosRequest request) {
      return blockingUnaryCall(
          getChannel(), getSavePushTokenIosMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * プッシュ通知_ON/OFF更新
     * </pre>
     */
    public com.google.protobuf.Empty updateNotify(com.talking.grpc.push_notify.UpdateNotifyRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateNotifyMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PushNotifyServiceFutureStub extends io.grpc.stub.AbstractStub<PushNotifyServiceFutureStub> {
    private PushNotifyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PushNotifyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PushNotifyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PushNotifyServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * プッシュ通知デバイストークン_iOS / 登録, 更新
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> savePushTokenIos(
        com.talking.grpc.push_notify.SavePushTokenIosRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSavePushTokenIosMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * プッシュ通知_ON/OFF更新
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateNotify(
        com.talking.grpc.push_notify.UpdateNotifyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateNotifyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_PUSH_TOKEN_IOS = 0;
  private static final int METHODID_UPDATE_NOTIFY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PushNotifyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PushNotifyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_PUSH_TOKEN_IOS:
          serviceImpl.savePushTokenIos((com.talking.grpc.push_notify.SavePushTokenIosRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_NOTIFY:
          serviceImpl.updateNotify((com.talking.grpc.push_notify.UpdateNotifyRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static abstract class PushNotifyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PushNotifyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.talking.grpc.push_notify.PushNotify.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PushNotifyService");
    }
  }

  private static final class PushNotifyServiceFileDescriptorSupplier
      extends PushNotifyServiceBaseDescriptorSupplier {
    PushNotifyServiceFileDescriptorSupplier() {}
  }

  private static final class PushNotifyServiceMethodDescriptorSupplier
      extends PushNotifyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PushNotifyServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PushNotifyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PushNotifyServiceFileDescriptorSupplier())
              .addMethod(getSavePushTokenIosMethod())
              .addMethod(getUpdateNotifyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
