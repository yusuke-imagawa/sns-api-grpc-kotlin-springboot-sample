package com.talking.grpc.calling;

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
    comments = "Source: calling.proto")
public final class CallingServiceGrpc {

  private CallingServiceGrpc() {}

  public static final String SERVICE_NAME = "calling.CallingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.calling.StartCallingRequest,
      com.talking.grpc.calling.StartCallingResponse> getStartCallingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartCalling",
      requestType = com.talking.grpc.calling.StartCallingRequest.class,
      responseType = com.talking.grpc.calling.StartCallingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.calling.StartCallingRequest,
      com.talking.grpc.calling.StartCallingResponse> getStartCallingMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.calling.StartCallingRequest, com.talking.grpc.calling.StartCallingResponse> getStartCallingMethod;
    if ((getStartCallingMethod = CallingServiceGrpc.getStartCallingMethod) == null) {
      synchronized (CallingServiceGrpc.class) {
        if ((getStartCallingMethod = CallingServiceGrpc.getStartCallingMethod) == null) {
          CallingServiceGrpc.getStartCallingMethod = getStartCallingMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.calling.StartCallingRequest, com.talking.grpc.calling.StartCallingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartCalling"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.StartCallingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.StartCallingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallingServiceMethodDescriptorSupplier("StartCalling"))
              .build();
        }
      }
    }
    return getStartCallingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.calling.ReceiveCallingRequest,
      com.google.protobuf.Empty> getReceiveCallingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReceiveCalling",
      requestType = com.talking.grpc.calling.ReceiveCallingRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.calling.ReceiveCallingRequest,
      com.google.protobuf.Empty> getReceiveCallingMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.calling.ReceiveCallingRequest, com.google.protobuf.Empty> getReceiveCallingMethod;
    if ((getReceiveCallingMethod = CallingServiceGrpc.getReceiveCallingMethod) == null) {
      synchronized (CallingServiceGrpc.class) {
        if ((getReceiveCallingMethod = CallingServiceGrpc.getReceiveCallingMethod) == null) {
          CallingServiceGrpc.getReceiveCallingMethod = getReceiveCallingMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.calling.ReceiveCallingRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReceiveCalling"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.ReceiveCallingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new CallingServiceMethodDescriptorSupplier("ReceiveCalling"))
              .build();
        }
      }
    }
    return getReceiveCallingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.calling.EndCallingRequest,
      com.google.protobuf.Empty> getEndCallingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EndCalling",
      requestType = com.talking.grpc.calling.EndCallingRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.calling.EndCallingRequest,
      com.google.protobuf.Empty> getEndCallingMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.calling.EndCallingRequest, com.google.protobuf.Empty> getEndCallingMethod;
    if ((getEndCallingMethod = CallingServiceGrpc.getEndCallingMethod) == null) {
      synchronized (CallingServiceGrpc.class) {
        if ((getEndCallingMethod = CallingServiceGrpc.getEndCallingMethod) == null) {
          CallingServiceGrpc.getEndCallingMethod = getEndCallingMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.calling.EndCallingRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EndCalling"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.EndCallingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new CallingServiceMethodDescriptorSupplier("EndCalling"))
              .build();
        }
      }
    }
    return getEndCallingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.calling.ContinueCallingRequest,
      com.google.protobuf.Empty> getContinueCallingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ContinueCalling",
      requestType = com.talking.grpc.calling.ContinueCallingRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.calling.ContinueCallingRequest,
      com.google.protobuf.Empty> getContinueCallingMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.calling.ContinueCallingRequest, com.google.protobuf.Empty> getContinueCallingMethod;
    if ((getContinueCallingMethod = CallingServiceGrpc.getContinueCallingMethod) == null) {
      synchronized (CallingServiceGrpc.class) {
        if ((getContinueCallingMethod = CallingServiceGrpc.getContinueCallingMethod) == null) {
          CallingServiceGrpc.getContinueCallingMethod = getContinueCallingMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.calling.ContinueCallingRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ContinueCalling"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.ContinueCallingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new CallingServiceMethodDescriptorSupplier("ContinueCalling"))
              .build();
        }
      }
    }
    return getContinueCallingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.talking.grpc.calling.StatusResponse> getReceiveCallingStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReceiveCallingStatus",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.talking.grpc.calling.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.talking.grpc.calling.StatusResponse> getReceiveCallingStatusMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.talking.grpc.calling.StatusResponse> getReceiveCallingStatusMethod;
    if ((getReceiveCallingStatusMethod = CallingServiceGrpc.getReceiveCallingStatusMethod) == null) {
      synchronized (CallingServiceGrpc.class) {
        if ((getReceiveCallingStatusMethod = CallingServiceGrpc.getReceiveCallingStatusMethod) == null) {
          CallingServiceGrpc.getReceiveCallingStatusMethod = getReceiveCallingStatusMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.talking.grpc.calling.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReceiveCallingStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.calling.StatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallingServiceMethodDescriptorSupplier("ReceiveCallingStatus"))
              .build();
        }
      }
    }
    return getReceiveCallingStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallingServiceStub newStub(io.grpc.Channel channel) {
    return new CallingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CallingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CallingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CallingServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 通話を掛ける。
     * </pre>
     */
    public void startCalling(com.talking.grpc.calling.StartCallingRequest request,
        io.grpc.stub.StreamObserver<com.talking.grpc.calling.StartCallingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartCallingMethod(), responseObserver);
    }

    /**
     * <pre>
     * 通話を受ける。
     * </pre>
     */
    public void receiveCalling(com.talking.grpc.calling.ReceiveCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveCallingMethod(), responseObserver);
    }

    /**
     * <pre>
     * 通話を切る。
     * </pre>
     */
    public void endCalling(com.talking.grpc.calling.EndCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getEndCallingMethod(), responseObserver);
    }

    /**
     * <pre>
     * 通話が継続していることを記録
     * </pre>
     */
    public void continueCalling(com.talking.grpc.calling.ContinueCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getContinueCallingMethod(), responseObserver);
    }

    /**
     * <pre>
     * 通話 / ステータス受信　ステータス: 開始、通話中、終了
     *    ・ステータスの変更時に受信する。
     *    ・clientからの接続時にも受信する。
     * </pre>
     */
    public void receiveCallingStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.talking.grpc.calling.StatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveCallingStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStartCallingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.calling.StartCallingRequest,
                com.talking.grpc.calling.StartCallingResponse>(
                  this, METHODID_START_CALLING)))
          .addMethod(
            getReceiveCallingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.calling.ReceiveCallingRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_RECEIVE_CALLING)))
          .addMethod(
            getEndCallingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.calling.EndCallingRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_END_CALLING)))
          .addMethod(
            getContinueCallingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.calling.ContinueCallingRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_CONTINUE_CALLING)))
          .addMethod(
            getReceiveCallingStatusMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.talking.grpc.calling.StatusResponse>(
                  this, METHODID_RECEIVE_CALLING_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class CallingServiceStub extends io.grpc.stub.AbstractStub<CallingServiceStub> {
    private CallingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallingServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 通話を掛ける。
     * </pre>
     */
    public void startCalling(com.talking.grpc.calling.StartCallingRequest request,
        io.grpc.stub.StreamObserver<com.talking.grpc.calling.StartCallingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartCallingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 通話を受ける。
     * </pre>
     */
    public void receiveCalling(com.talking.grpc.calling.ReceiveCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReceiveCallingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 通話を切る。
     * </pre>
     */
    public void endCalling(com.talking.grpc.calling.EndCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEndCallingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 通話が継続していることを記録
     * </pre>
     */
    public void continueCalling(com.talking.grpc.calling.ContinueCallingRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getContinueCallingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 通話 / ステータス受信　ステータス: 開始、通話中、終了
     *    ・ステータスの変更時に受信する。
     *    ・clientからの接続時にも受信する。
     * </pre>
     */
    public void receiveCallingStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.talking.grpc.calling.StatusResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveCallingStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CallingServiceBlockingStub extends io.grpc.stub.AbstractStub<CallingServiceBlockingStub> {
    private CallingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallingServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 通話を掛ける。
     * </pre>
     */
    public com.talking.grpc.calling.StartCallingResponse startCalling(com.talking.grpc.calling.StartCallingRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartCallingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 通話を受ける。
     * </pre>
     */
    public com.google.protobuf.Empty receiveCalling(com.talking.grpc.calling.ReceiveCallingRequest request) {
      return blockingUnaryCall(
          getChannel(), getReceiveCallingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 通話を切る。
     * </pre>
     */
    public com.google.protobuf.Empty endCalling(com.talking.grpc.calling.EndCallingRequest request) {
      return blockingUnaryCall(
          getChannel(), getEndCallingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 通話が継続していることを記録
     * </pre>
     */
    public com.google.protobuf.Empty continueCalling(com.talking.grpc.calling.ContinueCallingRequest request) {
      return blockingUnaryCall(
          getChannel(), getContinueCallingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 通話 / ステータス受信　ステータス: 開始、通話中、終了
     *    ・ステータスの変更時に受信する。
     *    ・clientからの接続時にも受信する。
     * </pre>
     */
    public java.util.Iterator<com.talking.grpc.calling.StatusResponse> receiveCallingStatus(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveCallingStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CallingServiceFutureStub extends io.grpc.stub.AbstractStub<CallingServiceFutureStub> {
    private CallingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CallingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CallingServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 通話を掛ける。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.talking.grpc.calling.StartCallingResponse> startCalling(
        com.talking.grpc.calling.StartCallingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartCallingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 通話を受ける。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> receiveCalling(
        com.talking.grpc.calling.ReceiveCallingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReceiveCallingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 通話を切る。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> endCalling(
        com.talking.grpc.calling.EndCallingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEndCallingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 通話が継続していることを記録
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> continueCalling(
        com.talking.grpc.calling.ContinueCallingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getContinueCallingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_START_CALLING = 0;
  private static final int METHODID_RECEIVE_CALLING = 1;
  private static final int METHODID_END_CALLING = 2;
  private static final int METHODID_CONTINUE_CALLING = 3;
  private static final int METHODID_RECEIVE_CALLING_STATUS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CallingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CallingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_START_CALLING:
          serviceImpl.startCalling((com.talking.grpc.calling.StartCallingRequest) request,
              (io.grpc.stub.StreamObserver<com.talking.grpc.calling.StartCallingResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_CALLING:
          serviceImpl.receiveCalling((com.talking.grpc.calling.ReceiveCallingRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_END_CALLING:
          serviceImpl.endCalling((com.talking.grpc.calling.EndCallingRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_CONTINUE_CALLING:
          serviceImpl.continueCalling((com.talking.grpc.calling.ContinueCallingRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_CALLING_STATUS:
          serviceImpl.receiveCallingStatus((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.talking.grpc.calling.StatusResponse>) responseObserver);
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

  private static abstract class CallingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.talking.grpc.calling.Calling.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CallingService");
    }
  }

  private static final class CallingServiceFileDescriptorSupplier
      extends CallingServiceBaseDescriptorSupplier {
    CallingServiceFileDescriptorSupplier() {}
  }

  private static final class CallingServiceMethodDescriptorSupplier
      extends CallingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CallingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CallingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallingServiceFileDescriptorSupplier())
              .addMethod(getStartCallingMethod())
              .addMethod(getReceiveCallingMethod())
              .addMethod(getEndCallingMethod())
              .addMethod(getContinueCallingMethod())
              .addMethod(getReceiveCallingStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
