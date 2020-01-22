package monitorstubs;

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
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: Monitor.proto")
public final class MonitorServiceGrpc {

  private MonitorServiceGrpc() {}

  public static final String SERVICE_NAME = "monitorservice.MonitorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      monitorstubs.InstanceNumber> getGetInstanceNumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getInstanceNum",
      requestType = com.google.protobuf.Empty.class,
      responseType = monitorstubs.InstanceNumber.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      monitorstubs.InstanceNumber> getGetInstanceNumMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, monitorstubs.InstanceNumber> getGetInstanceNumMethod;
    if ((getGetInstanceNumMethod = MonitorServiceGrpc.getGetInstanceNumMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getGetInstanceNumMethod = MonitorServiceGrpc.getGetInstanceNumMethod) == null) {
          MonitorServiceGrpc.getGetInstanceNumMethod = getGetInstanceNumMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, monitorstubs.InstanceNumber>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "getInstanceNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.InstanceNumber.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("getInstanceNum"))
                  .build();
          }
        }
     }
     return getGetInstanceNumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      monitorstubs.GlobalStatus> getGetGlobalStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getGlobalStatus",
      requestType = com.google.protobuf.Empty.class,
      responseType = monitorstubs.GlobalStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      monitorstubs.GlobalStatus> getGetGlobalStatusMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, monitorstubs.GlobalStatus> getGetGlobalStatusMethod;
    if ((getGetGlobalStatusMethod = MonitorServiceGrpc.getGetGlobalStatusMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getGetGlobalStatusMethod = MonitorServiceGrpc.getGetGlobalStatusMethod) == null) {
          MonitorServiceGrpc.getGetGlobalStatusMethod = getGetGlobalStatusMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, monitorstubs.GlobalStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "getGlobalStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.GlobalStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("getGlobalStatus"))
                  .build();
          }
        }
     }
     return getGetGlobalStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetCpuLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setCpuLimit",
      requestType = monitorstubs.MetricLimit.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetCpuLimitMethod() {
    io.grpc.MethodDescriptor<monitorstubs.MetricLimit, com.google.protobuf.Empty> getSetCpuLimitMethod;
    if ((getSetCpuLimitMethod = MonitorServiceGrpc.getSetCpuLimitMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getSetCpuLimitMethod = MonitorServiceGrpc.getSetCpuLimitMethod) == null) {
          MonitorServiceGrpc.getSetCpuLimitMethod = getSetCpuLimitMethod = 
              io.grpc.MethodDescriptor.<monitorstubs.MetricLimit, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "setCpuLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.MetricLimit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("setCpuLimit"))
                  .build();
          }
        }
     }
     return getSetCpuLimitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetMemLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setMemLimit",
      requestType = monitorstubs.MetricLimit.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetMemLimitMethod() {
    io.grpc.MethodDescriptor<monitorstubs.MetricLimit, com.google.protobuf.Empty> getSetMemLimitMethod;
    if ((getSetMemLimitMethod = MonitorServiceGrpc.getSetMemLimitMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getSetMemLimitMethod = MonitorServiceGrpc.getSetMemLimitMethod) == null) {
          MonitorServiceGrpc.getSetMemLimitMethod = getSetMemLimitMethod = 
              io.grpc.MethodDescriptor.<monitorstubs.MetricLimit, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "setMemLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.MetricLimit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("setMemLimit"))
                  .build();
          }
        }
     }
     return getSetMemLimitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetImgPerSecLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setImgPerSecLimit",
      requestType = monitorstubs.MetricLimit.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<monitorstubs.MetricLimit,
      com.google.protobuf.Empty> getSetImgPerSecLimitMethod() {
    io.grpc.MethodDescriptor<monitorstubs.MetricLimit, com.google.protobuf.Empty> getSetImgPerSecLimitMethod;
    if ((getSetImgPerSecLimitMethod = MonitorServiceGrpc.getSetImgPerSecLimitMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getSetImgPerSecLimitMethod = MonitorServiceGrpc.getSetImgPerSecLimitMethod) == null) {
          MonitorServiceGrpc.getSetImgPerSecLimitMethod = getSetImgPerSecLimitMethod = 
              io.grpc.MethodDescriptor.<monitorstubs.MetricLimit, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "setImgPerSecLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.MetricLimit.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("setImgPerSecLimit"))
                  .build();
          }
        }
     }
     return getSetImgPerSecLimitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<monitorstubs.ActionPeriod,
      com.google.protobuf.Empty> getSetActionPeriodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setActionPeriod",
      requestType = monitorstubs.ActionPeriod.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<monitorstubs.ActionPeriod,
      com.google.protobuf.Empty> getSetActionPeriodMethod() {
    io.grpc.MethodDescriptor<monitorstubs.ActionPeriod, com.google.protobuf.Empty> getSetActionPeriodMethod;
    if ((getSetActionPeriodMethod = MonitorServiceGrpc.getSetActionPeriodMethod) == null) {
      synchronized (MonitorServiceGrpc.class) {
        if ((getSetActionPeriodMethod = MonitorServiceGrpc.getSetActionPeriodMethod) == null) {
          MonitorServiceGrpc.getSetActionPeriodMethod = getSetActionPeriodMethod = 
              io.grpc.MethodDescriptor.<monitorstubs.ActionPeriod, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "monitorservice.MonitorService", "setActionPeriod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  monitorstubs.ActionPeriod.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MonitorServiceMethodDescriptorSupplier("setActionPeriod"))
                  .build();
          }
        }
     }
     return getSetActionPeriodMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MonitorServiceStub newStub(io.grpc.Channel channel) {
    return new MonitorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MonitorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MonitorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MonitorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MonitorServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class MonitorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getInstanceNum(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<monitorstubs.InstanceNumber> responseObserver) {
      asyncUnimplementedUnaryCall(getGetInstanceNumMethod(), responseObserver);
    }

    /**
     */
    public void getGlobalStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<monitorstubs.GlobalStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGlobalStatusMethod(), responseObserver);
    }

    /**
     */
    public void setCpuLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSetCpuLimitMethod(), responseObserver);
    }

    /**
     */
    public void setMemLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMemLimitMethod(), responseObserver);
    }

    /**
     */
    public void setImgPerSecLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSetImgPerSecLimitMethod(), responseObserver);
    }

    /**
     */
    public void setActionPeriod(monitorstubs.ActionPeriod request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSetActionPeriodMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetInstanceNumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                monitorstubs.InstanceNumber>(
                  this, METHODID_GET_INSTANCE_NUM)))
          .addMethod(
            getGetGlobalStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                monitorstubs.GlobalStatus>(
                  this, METHODID_GET_GLOBAL_STATUS)))
          .addMethod(
            getSetCpuLimitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                monitorstubs.MetricLimit,
                com.google.protobuf.Empty>(
                  this, METHODID_SET_CPU_LIMIT)))
          .addMethod(
            getSetMemLimitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                monitorstubs.MetricLimit,
                com.google.protobuf.Empty>(
                  this, METHODID_SET_MEM_LIMIT)))
          .addMethod(
            getSetImgPerSecLimitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                monitorstubs.MetricLimit,
                com.google.protobuf.Empty>(
                  this, METHODID_SET_IMG_PER_SEC_LIMIT)))
          .addMethod(
            getSetActionPeriodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                monitorstubs.ActionPeriod,
                com.google.protobuf.Empty>(
                  this, METHODID_SET_ACTION_PERIOD)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MonitorServiceStub extends io.grpc.stub.AbstractStub<MonitorServiceStub> {
    private MonitorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitorServiceStub(channel, callOptions);
    }

    /**
     */
    public void getInstanceNum(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<monitorstubs.InstanceNumber> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetInstanceNumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGlobalStatus(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<monitorstubs.GlobalStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGlobalStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setCpuLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetCpuLimitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setMemLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMemLimitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setImgPerSecLimit(monitorstubs.MetricLimit request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetImgPerSecLimitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setActionPeriod(monitorstubs.ActionPeriod request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetActionPeriodMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MonitorServiceBlockingStub extends io.grpc.stub.AbstractStub<MonitorServiceBlockingStub> {
    private MonitorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public monitorstubs.InstanceNumber getInstanceNum(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetInstanceNumMethod(), getCallOptions(), request);
    }

    /**
     */
    public monitorstubs.GlobalStatus getGlobalStatus(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetGlobalStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setCpuLimit(monitorstubs.MetricLimit request) {
      return blockingUnaryCall(
          getChannel(), getSetCpuLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setMemLimit(monitorstubs.MetricLimit request) {
      return blockingUnaryCall(
          getChannel(), getSetMemLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setImgPerSecLimit(monitorstubs.MetricLimit request) {
      return blockingUnaryCall(
          getChannel(), getSetImgPerSecLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty setActionPeriod(monitorstubs.ActionPeriod request) {
      return blockingUnaryCall(
          getChannel(), getSetActionPeriodMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MonitorServiceFutureStub extends io.grpc.stub.AbstractStub<MonitorServiceFutureStub> {
    private MonitorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MonitorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonitorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MonitorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<monitorstubs.InstanceNumber> getInstanceNum(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetInstanceNumMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<monitorstubs.GlobalStatus> getGlobalStatus(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGlobalStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setCpuLimit(
        monitorstubs.MetricLimit request) {
      return futureUnaryCall(
          getChannel().newCall(getSetCpuLimitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setMemLimit(
        monitorstubs.MetricLimit request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMemLimitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setImgPerSecLimit(
        monitorstubs.MetricLimit request) {
      return futureUnaryCall(
          getChannel().newCall(getSetImgPerSecLimitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> setActionPeriod(
        monitorstubs.ActionPeriod request) {
      return futureUnaryCall(
          getChannel().newCall(getSetActionPeriodMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_INSTANCE_NUM = 0;
  private static final int METHODID_GET_GLOBAL_STATUS = 1;
  private static final int METHODID_SET_CPU_LIMIT = 2;
  private static final int METHODID_SET_MEM_LIMIT = 3;
  private static final int METHODID_SET_IMG_PER_SEC_LIMIT = 4;
  private static final int METHODID_SET_ACTION_PERIOD = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MonitorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MonitorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_INSTANCE_NUM:
          serviceImpl.getInstanceNum((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<monitorstubs.InstanceNumber>) responseObserver);
          break;
        case METHODID_GET_GLOBAL_STATUS:
          serviceImpl.getGlobalStatus((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<monitorstubs.GlobalStatus>) responseObserver);
          break;
        case METHODID_SET_CPU_LIMIT:
          serviceImpl.setCpuLimit((monitorstubs.MetricLimit) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_SET_MEM_LIMIT:
          serviceImpl.setMemLimit((monitorstubs.MetricLimit) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_SET_IMG_PER_SEC_LIMIT:
          serviceImpl.setImgPerSecLimit((monitorstubs.MetricLimit) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_SET_ACTION_PERIOD:
          serviceImpl.setActionPeriod((monitorstubs.ActionPeriod) request,
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

  private static abstract class MonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MonitorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return monitorstubs.Monitor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MonitorService");
    }
  }

  private static final class MonitorServiceFileDescriptorSupplier
      extends MonitorServiceBaseDescriptorSupplier {
    MonitorServiceFileDescriptorSupplier() {}
  }

  private static final class MonitorServiceMethodDescriptorSupplier
      extends MonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MonitorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MonitorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MonitorServiceFileDescriptorSupplier())
              .addMethod(getGetInstanceNumMethod())
              .addMethod(getGetGlobalStatusMethod())
              .addMethod(getSetCpuLimitMethod())
              .addMethod(getSetMemLimitMethod())
              .addMethod(getSetImgPerSecLimitMethod())
              .addMethod(getSetActionPeriodMethod())
              .build();
        }
      }
    }
    return result;
  }
}
