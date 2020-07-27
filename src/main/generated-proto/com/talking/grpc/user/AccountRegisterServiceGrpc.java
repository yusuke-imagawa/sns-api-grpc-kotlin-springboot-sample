package com.talking.grpc.user;

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
    comments = "Source: account.proto")
public final class AccountRegisterServiceGrpc {

  private AccountRegisterServiceGrpc() {}

  public static final String SERVICE_NAME = "account.AccountRegisterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.user.RegisterUserRequest,
      com.talking.grpc.user.RegisterUserResponse> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = com.talking.grpc.user.RegisterUserRequest.class,
      responseType = com.talking.grpc.user.RegisterUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.user.RegisterUserRequest,
      com.talking.grpc.user.RegisterUserResponse> getRegisterMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.user.RegisterUserRequest, com.talking.grpc.user.RegisterUserResponse> getRegisterMethod;
    if ((getRegisterMethod = AccountRegisterServiceGrpc.getRegisterMethod) == null) {
      synchronized (AccountRegisterServiceGrpc.class) {
        if ((getRegisterMethod = AccountRegisterServiceGrpc.getRegisterMethod) == null) {
          AccountRegisterServiceGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.user.RegisterUserRequest, com.talking.grpc.user.RegisterUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.user.RegisterUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.user.RegisterUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountRegisterServiceMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountRegisterServiceStub newStub(io.grpc.Channel channel) {
    return new AccountRegisterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountRegisterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AccountRegisterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountRegisterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AccountRegisterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AccountRegisterServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ユーザー/登録
     * </pre>
     */
    public void register(com.talking.grpc.user.RegisterUserRequest request,
        io.grpc.stub.StreamObserver<com.talking.grpc.user.RegisterUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.user.RegisterUserRequest,
                com.talking.grpc.user.RegisterUserResponse>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   */
  public static final class AccountRegisterServiceStub extends io.grpc.stub.AbstractStub<AccountRegisterServiceStub> {
    private AccountRegisterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegisterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegisterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegisterServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * ユーザー/登録
     * </pre>
     */
    public void register(com.talking.grpc.user.RegisterUserRequest request,
        io.grpc.stub.StreamObserver<com.talking.grpc.user.RegisterUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AccountRegisterServiceBlockingStub extends io.grpc.stub.AbstractStub<AccountRegisterServiceBlockingStub> {
    private AccountRegisterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegisterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegisterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegisterServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ユーザー/登録
     * </pre>
     */
    public com.talking.grpc.user.RegisterUserResponse register(com.talking.grpc.user.RegisterUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AccountRegisterServiceFutureStub extends io.grpc.stub.AbstractStub<AccountRegisterServiceFutureStub> {
    private AccountRegisterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegisterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegisterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegisterServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ユーザー/登録
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.talking.grpc.user.RegisterUserResponse> register(
        com.talking.grpc.user.RegisterUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountRegisterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AccountRegisterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.talking.grpc.user.RegisterUserRequest) request,
              (io.grpc.stub.StreamObserver<com.talking.grpc.user.RegisterUserResponse>) responseObserver);
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

  private static abstract class AccountRegisterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountRegisterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.talking.grpc.user.Account.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountRegisterService");
    }
  }

  private static final class AccountRegisterServiceFileDescriptorSupplier
      extends AccountRegisterServiceBaseDescriptorSupplier {
    AccountRegisterServiceFileDescriptorSupplier() {}
  }

  private static final class AccountRegisterServiceMethodDescriptorSupplier
      extends AccountRegisterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountRegisterServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountRegisterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountRegisterServiceFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
