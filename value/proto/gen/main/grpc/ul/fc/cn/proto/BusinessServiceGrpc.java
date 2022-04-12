package ul.fc.cn.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.2)",
    comments = "Source: business.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BusinessServiceGrpc {

  private BusinessServiceGrpc() {}

  public static final String SERVICE_NAME = "ul.fc.cn.proto.BusinessService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.Business,
      ul.fc.cn.proto.BusinessOuterClass.BusinessId> getAddBusinessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addBusiness",
      requestType = ul.fc.cn.proto.BusinessOuterClass.Business.class,
      responseType = ul.fc.cn.proto.BusinessOuterClass.BusinessId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.Business,
      ul.fc.cn.proto.BusinessOuterClass.BusinessId> getAddBusinessMethod() {
    io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.Business, ul.fc.cn.proto.BusinessOuterClass.BusinessId> getAddBusinessMethod;
    if ((getAddBusinessMethod = BusinessServiceGrpc.getAddBusinessMethod) == null) {
      synchronized (BusinessServiceGrpc.class) {
        if ((getAddBusinessMethod = BusinessServiceGrpc.getAddBusinessMethod) == null) {
          BusinessServiceGrpc.getAddBusinessMethod = getAddBusinessMethod =
              io.grpc.MethodDescriptor.<ul.fc.cn.proto.BusinessOuterClass.Business, ul.fc.cn.proto.BusinessOuterClass.BusinessId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addBusiness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.Business.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.BusinessId.getDefaultInstance()))
              .setSchemaDescriptor(new BusinessServiceMethodDescriptorSupplier("addBusiness"))
              .build();
        }
      }
    }
    return getAddBusinessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId,
      ul.fc.cn.proto.BusinessOuterClass.Business> getGetBusinessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBusiness",
      requestType = ul.fc.cn.proto.BusinessOuterClass.BusinessId.class,
      responseType = ul.fc.cn.proto.BusinessOuterClass.Business.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId,
      ul.fc.cn.proto.BusinessOuterClass.Business> getGetBusinessMethod() {
    io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId, ul.fc.cn.proto.BusinessOuterClass.Business> getGetBusinessMethod;
    if ((getGetBusinessMethod = BusinessServiceGrpc.getGetBusinessMethod) == null) {
      synchronized (BusinessServiceGrpc.class) {
        if ((getGetBusinessMethod = BusinessServiceGrpc.getGetBusinessMethod) == null) {
          BusinessServiceGrpc.getGetBusinessMethod = getGetBusinessMethod =
              io.grpc.MethodDescriptor.<ul.fc.cn.proto.BusinessOuterClass.BusinessId, ul.fc.cn.proto.BusinessOuterClass.Business>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBusiness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.BusinessId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.Business.getDefaultInstance()))
              .setSchemaDescriptor(new BusinessServiceMethodDescriptorSupplier("getBusiness"))
              .build();
        }
      }
    }
    return getGetBusinessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest,
      ul.fc.cn.proto.BusinessOuterClass.Business> getUpdateBusinessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateBusiness",
      requestType = ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest.class,
      responseType = ul.fc.cn.proto.BusinessOuterClass.Business.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest,
      ul.fc.cn.proto.BusinessOuterClass.Business> getUpdateBusinessMethod() {
    io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest, ul.fc.cn.proto.BusinessOuterClass.Business> getUpdateBusinessMethod;
    if ((getUpdateBusinessMethod = BusinessServiceGrpc.getUpdateBusinessMethod) == null) {
      synchronized (BusinessServiceGrpc.class) {
        if ((getUpdateBusinessMethod = BusinessServiceGrpc.getUpdateBusinessMethod) == null) {
          BusinessServiceGrpc.getUpdateBusinessMethod = getUpdateBusinessMethod =
              io.grpc.MethodDescriptor.<ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest, ul.fc.cn.proto.BusinessOuterClass.Business>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateBusiness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.Business.getDefaultInstance()))
              .setSchemaDescriptor(new BusinessServiceMethodDescriptorSupplier("updateBusiness"))
              .build();
        }
      }
    }
    return getUpdateBusinessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId,
      ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getDeleteBusinessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteBusiness",
      requestType = ul.fc.cn.proto.BusinessOuterClass.BusinessId.class,
      responseType = ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId,
      ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getDeleteBusinessMethod() {
    io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.BusinessId, ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getDeleteBusinessMethod;
    if ((getDeleteBusinessMethod = BusinessServiceGrpc.getDeleteBusinessMethod) == null) {
      synchronized (BusinessServiceGrpc.class) {
        if ((getDeleteBusinessMethod = BusinessServiceGrpc.getDeleteBusinessMethod) == null) {
          BusinessServiceGrpc.getDeleteBusinessMethod = getDeleteBusinessMethod =
              io.grpc.MethodDescriptor.<ul.fc.cn.proto.BusinessOuterClass.BusinessId, ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteBusiness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.BusinessId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BusinessServiceMethodDescriptorSupplier("deleteBusiness"))
              .build();
        }
      }
    }
    return getDeleteBusinessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest,
      ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getSearchBusinessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchBusiness",
      requestType = ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest.class,
      responseType = ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest,
      ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getSearchBusinessMethod() {
    io.grpc.MethodDescriptor<ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest, ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> getSearchBusinessMethod;
    if ((getSearchBusinessMethod = BusinessServiceGrpc.getSearchBusinessMethod) == null) {
      synchronized (BusinessServiceGrpc.class) {
        if ((getSearchBusinessMethod = BusinessServiceGrpc.getSearchBusinessMethod) == null) {
          BusinessServiceGrpc.getSearchBusinessMethod = getSearchBusinessMethod =
              io.grpc.MethodDescriptor.<ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest, ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "searchBusiness"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BusinessServiceMethodDescriptorSupplier("searchBusiness"))
              .build();
        }
      }
    }
    return getSearchBusinessMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BusinessServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BusinessServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BusinessServiceStub>() {
        @java.lang.Override
        public BusinessServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BusinessServiceStub(channel, callOptions);
        }
      };
    return BusinessServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BusinessServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BusinessServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BusinessServiceBlockingStub>() {
        @java.lang.Override
        public BusinessServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BusinessServiceBlockingStub(channel, callOptions);
        }
      };
    return BusinessServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BusinessServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BusinessServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BusinessServiceFutureStub>() {
        @java.lang.Override
        public BusinessServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BusinessServiceFutureStub(channel, callOptions);
        }
      };
    return BusinessServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class BusinessServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addBusiness(ul.fc.cn.proto.BusinessOuterClass.Business request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.BusinessId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddBusinessMethod(), responseObserver);
    }

    /**
     */
    public void getBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBusinessMethod(), responseObserver);
    }

    /**
     */
    public void updateBusiness(ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBusinessMethod(), responseObserver);
    }

    /**
     */
    public void deleteBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteBusinessMethod(), responseObserver);
    }

    /**
     */
    public void searchBusiness(ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchBusinessMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddBusinessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ul.fc.cn.proto.BusinessOuterClass.Business,
                ul.fc.cn.proto.BusinessOuterClass.BusinessId>(
                  this, METHODID_ADD_BUSINESS)))
          .addMethod(
            getGetBusinessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ul.fc.cn.proto.BusinessOuterClass.BusinessId,
                ul.fc.cn.proto.BusinessOuterClass.Business>(
                  this, METHODID_GET_BUSINESS)))
          .addMethod(
            getUpdateBusinessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest,
                ul.fc.cn.proto.BusinessOuterClass.Business>(
                  this, METHODID_UPDATE_BUSINESS)))
          .addMethod(
            getDeleteBusinessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ul.fc.cn.proto.BusinessOuterClass.BusinessId,
                ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>(
                  this, METHODID_DELETE_BUSINESS)))
          .addMethod(
            getSearchBusinessMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest,
                ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>(
                  this, METHODID_SEARCH_BUSINESS)))
          .build();
    }
  }

  /**
   */
  public static final class BusinessServiceStub extends io.grpc.stub.AbstractAsyncStub<BusinessServiceStub> {
    private BusinessServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BusinessServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BusinessServiceStub(channel, callOptions);
    }

    /**
     */
    public void addBusiness(ul.fc.cn.proto.BusinessOuterClass.Business request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.BusinessId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddBusinessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBusinessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBusiness(ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateBusinessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteBusinessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchBusiness(ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest request,
        io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchBusinessMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BusinessServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<BusinessServiceBlockingStub> {
    private BusinessServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BusinessServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BusinessServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ul.fc.cn.proto.BusinessOuterClass.BusinessId addBusiness(ul.fc.cn.proto.BusinessOuterClass.Business request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddBusinessMethod(), getCallOptions(), request);
    }

    /**
     */
    public ul.fc.cn.proto.BusinessOuterClass.Business getBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBusinessMethod(), getCallOptions(), request);
    }

    /**
     */
    public ul.fc.cn.proto.BusinessOuterClass.Business updateBusiness(ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateBusinessMethod(), getCallOptions(), request);
    }

    /**
     */
    public ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse deleteBusiness(ul.fc.cn.proto.BusinessOuterClass.BusinessId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteBusinessMethod(), getCallOptions(), request);
    }

    /**
     */
    public ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse searchBusiness(ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchBusinessMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BusinessServiceFutureStub extends io.grpc.stub.AbstractFutureStub<BusinessServiceFutureStub> {
    private BusinessServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BusinessServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BusinessServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ul.fc.cn.proto.BusinessOuterClass.BusinessId> addBusiness(
        ul.fc.cn.proto.BusinessOuterClass.Business request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddBusinessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ul.fc.cn.proto.BusinessOuterClass.Business> getBusiness(
        ul.fc.cn.proto.BusinessOuterClass.BusinessId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBusinessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ul.fc.cn.proto.BusinessOuterClass.Business> updateBusiness(
        ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateBusinessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> deleteBusiness(
        ul.fc.cn.proto.BusinessOuterClass.BusinessId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteBusinessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse> searchBusiness(
        ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchBusinessMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_BUSINESS = 0;
  private static final int METHODID_GET_BUSINESS = 1;
  private static final int METHODID_UPDATE_BUSINESS = 2;
  private static final int METHODID_DELETE_BUSINESS = 3;
  private static final int METHODID_SEARCH_BUSINESS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BusinessServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BusinessServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_BUSINESS:
          serviceImpl.addBusiness((ul.fc.cn.proto.BusinessOuterClass.Business) request,
              (io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.BusinessId>) responseObserver);
          break;
        case METHODID_GET_BUSINESS:
          serviceImpl.getBusiness((ul.fc.cn.proto.BusinessOuterClass.BusinessId) request,
              (io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business>) responseObserver);
          break;
        case METHODID_UPDATE_BUSINESS:
          serviceImpl.updateBusiness((ul.fc.cn.proto.BusinessOuterClass.UpdateBusinessRequest) request,
              (io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.Business>) responseObserver);
          break;
        case METHODID_DELETE_BUSINESS:
          serviceImpl.deleteBusiness((ul.fc.cn.proto.BusinessOuterClass.BusinessId) request,
              (io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>) responseObserver);
          break;
        case METHODID_SEARCH_BUSINESS:
          serviceImpl.searchBusiness((ul.fc.cn.proto.BusinessOuterClass.SearchBusinessRequest) request,
              (io.grpc.stub.StreamObserver<ul.fc.cn.proto.BusinessOuterClass.DeleteBusinessResponse>) responseObserver);
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

  private static abstract class BusinessServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BusinessServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ul.fc.cn.proto.BusinessOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BusinessService");
    }
  }

  private static final class BusinessServiceFileDescriptorSupplier
      extends BusinessServiceBaseDescriptorSupplier {
    BusinessServiceFileDescriptorSupplier() {}
  }

  private static final class BusinessServiceMethodDescriptorSupplier
      extends BusinessServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BusinessServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BusinessServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BusinessServiceFileDescriptorSupplier())
              .addMethod(getAddBusinessMethod())
              .addMethod(getGetBusinessMethod())
              .addMethod(getUpdateBusinessMethod())
              .addMethod(getDeleteBusinessMethod())
              .addMethod(getSearchBusinessMethod())
              .build();
        }
      }
    }
    return result;
  }
}
