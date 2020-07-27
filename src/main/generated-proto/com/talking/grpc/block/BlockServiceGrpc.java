package com.talking.grpc.block;

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
    comments = "Source: block.proto")
public final class BlockServiceGrpc {

  private BlockServiceGrpc() {}

  public static final String SERVICE_NAME = "block.BlockService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.talking.grpc.block.BlockRequest,
      com.google.protobuf.Empty> getBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Block",
      requestType = com.talking.grpc.block.BlockRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.talking.grpc.block.BlockRequest,
      com.google.protobuf.Empty> getBlockMethod() {
    io.grpc.MethodDescriptor<com.talking.grpc.block.BlockRequest, com.google.protobuf.Empty> getBlockMethod;
    if ((getBlockMethod = BlockServiceGrpc.getBlockMethod) == null) {
      synchronized (BlockServiceGrpc.class) {
        if ((getBlockMethod = BlockServiceGrpc.getBlockMethod) == null) {
          BlockServiceGrpc.getBlockMethod = getBlockMethod =
              io.grpc.MethodDescriptor.<com.talking.grpc.block.BlockRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Block"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.talking.grpc.block.BlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new BlockServiceMethodDescriptorSupplier("Block"))
              .build();
        }
      }
    }
    return getBlockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BlockServiceStub newStub(io.grpc.Channel channel) {
    return new BlockServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BlockServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BlockServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BlockServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BlockServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BlockServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * ブロック
     * </pre>
     */
    public void block(com.talking.grpc.block.BlockRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.talking.grpc.block.BlockRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_BLOCK)))
          .build();
    }
  }

  /**
   */
  public static final class BlockServiceStub extends io.grpc.stub.AbstractStub<BlockServiceStub> {
    private BlockServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlockServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlockServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * ブロック
     * </pre>
     */
    public void block(com.talking.grpc.block.BlockRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BlockServiceBlockingStub extends io.grpc.stub.AbstractStub<BlockServiceBlockingStub> {
    private BlockServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlockServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlockServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * ブロック
     * </pre>
     */
    public com.google.protobuf.Empty block(com.talking.grpc.block.BlockRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BlockServiceFutureStub extends io.grpc.stub.AbstractStub<BlockServiceFutureStub> {
    private BlockServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BlockServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BlockServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BlockServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * ブロック
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> block(
        com.talking.grpc.block.BlockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BLOCK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BlockServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BlockServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BLOCK:
          serviceImpl.block((com.talking.grpc.block.BlockRequest) request,
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

  private static abstract class BlockServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BlockServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.talking.grpc.block.Block.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BlockService");
    }
  }

  private static final class BlockServiceFileDescriptorSupplier
      extends BlockServiceBaseDescriptorSupplier {
    BlockServiceFileDescriptorSupplier() {}
  }

  private static final class BlockServiceMethodDescriptorSupplier
      extends BlockServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BlockServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BlockServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BlockServiceFileDescriptorSupplier())
              .addMethod(getBlockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
