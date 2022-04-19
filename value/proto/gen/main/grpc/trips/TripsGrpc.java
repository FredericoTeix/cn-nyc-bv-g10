package trips;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.2)",
    comments = "Source: trips.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TripsGrpc {

  private TripsGrpc() {}

  public static final String SERVICE_NAME = "trips.Trips";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.Trip,
      trips.TripsOuterClass.TripID> getAddTripMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddTrip",
      requestType = trips.TripsOuterClass.Trip.class,
      responseType = trips.TripsOuterClass.TripID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.Trip,
      trips.TripsOuterClass.TripID> getAddTripMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.Trip, trips.TripsOuterClass.TripID> getAddTripMethod;
    if ((getAddTripMethod = TripsGrpc.getAddTripMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getAddTripMethod = TripsGrpc.getAddTripMethod) == null) {
          TripsGrpc.getAddTripMethod = getAddTripMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.Trip, trips.TripsOuterClass.TripID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddTrip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.Trip.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.TripID.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("AddTrip"))
              .build();
        }
      }
    }
    return getAddTripMethod;
  }

  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.LocationID,
      trips.TripsOuterClass.Location> getGetLocationByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLocationById",
      requestType = trips.TripsOuterClass.LocationID.class,
      responseType = trips.TripsOuterClass.Location.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.LocationID,
      trips.TripsOuterClass.Location> getGetLocationByIdMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.LocationID, trips.TripsOuterClass.Location> getGetLocationByIdMethod;
    if ((getGetLocationByIdMethod = TripsGrpc.getGetLocationByIdMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getGetLocationByIdMethod = TripsGrpc.getGetLocationByIdMethod) == null) {
          TripsGrpc.getGetLocationByIdMethod = getGetLocationByIdMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.LocationID, trips.TripsOuterClass.Location>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLocationById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.LocationID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.Location.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("GetLocationById"))
              .build();
        }
      }
    }
    return getGetLocationByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.City,
      trips.TripsOuterClass.LocationID> getGetLocationByCityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLocationByCity",
      requestType = trips.TripsOuterClass.City.class,
      responseType = trips.TripsOuterClass.LocationID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.City,
      trips.TripsOuterClass.LocationID> getGetLocationByCityMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.City, trips.TripsOuterClass.LocationID> getGetLocationByCityMethod;
    if ((getGetLocationByCityMethod = TripsGrpc.getGetLocationByCityMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getGetLocationByCityMethod = TripsGrpc.getGetLocationByCityMethod) == null) {
          TripsGrpc.getGetLocationByCityMethod = getGetLocationByCityMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.City, trips.TripsOuterClass.LocationID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLocationByCity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.City.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.LocationID.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("GetLocationByCity"))
              .build();
        }
      }
    }
    return getGetLocationByCityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.TripID,
      com.google.protobuf.Empty> getRemoveTripMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveTrip",
      requestType = trips.TripsOuterClass.TripID.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.TripID,
      com.google.protobuf.Empty> getRemoveTripMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.TripID, com.google.protobuf.Empty> getRemoveTripMethod;
    if ((getRemoveTripMethod = TripsGrpc.getRemoveTripMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getRemoveTripMethod = TripsGrpc.getRemoveTripMethod) == null) {
          TripsGrpc.getRemoveTripMethod = getRemoveTripMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.TripID, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveTrip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.TripID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("RemoveTrip"))
              .build();
        }
      }
    }
    return getRemoveTripMethod;
  }

  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.UpdateTripRequest,
      trips.TripsOuterClass.Trip> getUpdateTripMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTrip",
      requestType = trips.TripsOuterClass.UpdateTripRequest.class,
      responseType = trips.TripsOuterClass.Trip.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.UpdateTripRequest,
      trips.TripsOuterClass.Trip> getUpdateTripMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.UpdateTripRequest, trips.TripsOuterClass.Trip> getUpdateTripMethod;
    if ((getUpdateTripMethod = TripsGrpc.getUpdateTripMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getUpdateTripMethod = TripsGrpc.getUpdateTripMethod) == null) {
          TripsGrpc.getUpdateTripMethod = getUpdateTripMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.UpdateTripRequest, trips.TripsOuterClass.Trip>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTrip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.UpdateTripRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.Trip.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("UpdateTrip"))
              .build();
        }
      }
    }
    return getUpdateTripMethod;
  }

  private static volatile io.grpc.MethodDescriptor<trips.TripsOuterClass.GetCountTripsInLocationRequest,
      trips.TripsOuterClass.GetCountTripsInLocationResponse> getGetCountTripsInLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCountTripsInLocation",
      requestType = trips.TripsOuterClass.GetCountTripsInLocationRequest.class,
      responseType = trips.TripsOuterClass.GetCountTripsInLocationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<trips.TripsOuterClass.GetCountTripsInLocationRequest,
      trips.TripsOuterClass.GetCountTripsInLocationResponse> getGetCountTripsInLocationMethod() {
    io.grpc.MethodDescriptor<trips.TripsOuterClass.GetCountTripsInLocationRequest, trips.TripsOuterClass.GetCountTripsInLocationResponse> getGetCountTripsInLocationMethod;
    if ((getGetCountTripsInLocationMethod = TripsGrpc.getGetCountTripsInLocationMethod) == null) {
      synchronized (TripsGrpc.class) {
        if ((getGetCountTripsInLocationMethod = TripsGrpc.getGetCountTripsInLocationMethod) == null) {
          TripsGrpc.getGetCountTripsInLocationMethod = getGetCountTripsInLocationMethod =
              io.grpc.MethodDescriptor.<trips.TripsOuterClass.GetCountTripsInLocationRequest, trips.TripsOuterClass.GetCountTripsInLocationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCountTripsInLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.GetCountTripsInLocationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  trips.TripsOuterClass.GetCountTripsInLocationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripsMethodDescriptorSupplier("GetCountTripsInLocation"))
              .build();
        }
      }
    }
    return getGetCountTripsInLocationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TripsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripsStub>() {
        @java.lang.Override
        public TripsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripsStub(channel, callOptions);
        }
      };
    return TripsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TripsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripsBlockingStub>() {
        @java.lang.Override
        public TripsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripsBlockingStub(channel, callOptions);
        }
      };
    return TripsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TripsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripsFutureStub>() {
        @java.lang.Override
        public TripsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripsFutureStub(channel, callOptions);
        }
      };
    return TripsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TripsImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Add a trip to the data used to calculate the value
     * </pre>
     */
    public void addTrip(trips.TripsOuterClass.Trip request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.TripID> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddTripMethod(), responseObserver);
    }

    /**
     * <pre>
     * Find location description by ID
     * 
     * Returns LocationID object.
     * </pre>
     */
    public void getLocationById(trips.TripsOuterClass.LocationID request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.Location> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLocationByIdMethod(), responseObserver);
    }

    /**
     */
    public void getLocationByCity(trips.TripsOuterClass.City request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.LocationID> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLocationByCityMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove a trip in the data used to calculate the value
     * </pre>
     */
    public void removeTrip(trips.TripsOuterClass.TripID request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveTripMethod(), responseObserver);
    }

    /**
     * <pre>
     * Change a trip in the data used to calculate the value
     * </pre>
     */
    public void updateTrip(trips.TripsOuterClass.UpdateTripRequest request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.Trip> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateTripMethod(), responseObserver);
    }

    /**
     */
    public void getCountTripsInLocation(trips.TripsOuterClass.GetCountTripsInLocationRequest request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.GetCountTripsInLocationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCountTripsInLocationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddTripMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.Trip,
                trips.TripsOuterClass.TripID>(
                  this, METHODID_ADD_TRIP)))
          .addMethod(
            getGetLocationByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.LocationID,
                trips.TripsOuterClass.Location>(
                  this, METHODID_GET_LOCATION_BY_ID)))
          .addMethod(
            getGetLocationByCityMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.City,
                trips.TripsOuterClass.LocationID>(
                  this, METHODID_GET_LOCATION_BY_CITY)))
          .addMethod(
            getRemoveTripMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.TripID,
                com.google.protobuf.Empty>(
                  this, METHODID_REMOVE_TRIP)))
          .addMethod(
            getUpdateTripMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.UpdateTripRequest,
                trips.TripsOuterClass.Trip>(
                  this, METHODID_UPDATE_TRIP)))
          .addMethod(
            getGetCountTripsInLocationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                trips.TripsOuterClass.GetCountTripsInLocationRequest,
                trips.TripsOuterClass.GetCountTripsInLocationResponse>(
                  this, METHODID_GET_COUNT_TRIPS_IN_LOCATION)))
          .build();
    }
  }

  /**
   */
  public static final class TripsStub extends io.grpc.stub.AbstractAsyncStub<TripsStub> {
    private TripsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripsStub(channel, callOptions);
    }

    /**
     * <pre>
     * Add a trip to the data used to calculate the value
     * </pre>
     */
    public void addTrip(trips.TripsOuterClass.Trip request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.TripID> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddTripMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Find location description by ID
     * 
     * Returns LocationID object.
     * </pre>
     */
    public void getLocationById(trips.TripsOuterClass.LocationID request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.Location> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLocationByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLocationByCity(trips.TripsOuterClass.City request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.LocationID> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLocationByCityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove a trip in the data used to calculate the value
     * </pre>
     */
    public void removeTrip(trips.TripsOuterClass.TripID request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveTripMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Change a trip in the data used to calculate the value
     * </pre>
     */
    public void updateTrip(trips.TripsOuterClass.UpdateTripRequest request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.Trip> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateTripMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCountTripsInLocation(trips.TripsOuterClass.GetCountTripsInLocationRequest request,
        io.grpc.stub.StreamObserver<trips.TripsOuterClass.GetCountTripsInLocationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCountTripsInLocationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TripsBlockingStub extends io.grpc.stub.AbstractBlockingStub<TripsBlockingStub> {
    private TripsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Add a trip to the data used to calculate the value
     * </pre>
     */
    public trips.TripsOuterClass.TripID addTrip(trips.TripsOuterClass.Trip request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddTripMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Find location description by ID
     * 
     * Returns LocationID object.
     * </pre>
     */
    public trips.TripsOuterClass.Location getLocationById(trips.TripsOuterClass.LocationID request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLocationByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public trips.TripsOuterClass.LocationID getLocationByCity(trips.TripsOuterClass.City request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLocationByCityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove a trip in the data used to calculate the value
     * </pre>
     */
    public com.google.protobuf.Empty removeTrip(trips.TripsOuterClass.TripID request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveTripMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Change a trip in the data used to calculate the value
     * </pre>
     */
    public trips.TripsOuterClass.Trip updateTrip(trips.TripsOuterClass.UpdateTripRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateTripMethod(), getCallOptions(), request);
    }

    /**
     */
    public trips.TripsOuterClass.GetCountTripsInLocationResponse getCountTripsInLocation(trips.TripsOuterClass.GetCountTripsInLocationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCountTripsInLocationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TripsFutureStub extends io.grpc.stub.AbstractFutureStub<TripsFutureStub> {
    private TripsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Add a trip to the data used to calculate the value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<trips.TripsOuterClass.TripID> addTrip(
        trips.TripsOuterClass.Trip request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddTripMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Find location description by ID
     * 
     * Returns LocationID object.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<trips.TripsOuterClass.Location> getLocationById(
        trips.TripsOuterClass.LocationID request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLocationByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<trips.TripsOuterClass.LocationID> getLocationByCity(
        trips.TripsOuterClass.City request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLocationByCityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove a trip in the data used to calculate the value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> removeTrip(
        trips.TripsOuterClass.TripID request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveTripMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Change a trip in the data used to calculate the value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<trips.TripsOuterClass.Trip> updateTrip(
        trips.TripsOuterClass.UpdateTripRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateTripMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<trips.TripsOuterClass.GetCountTripsInLocationResponse> getCountTripsInLocation(
        trips.TripsOuterClass.GetCountTripsInLocationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCountTripsInLocationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TRIP = 0;
  private static final int METHODID_GET_LOCATION_BY_ID = 1;
  private static final int METHODID_GET_LOCATION_BY_CITY = 2;
  private static final int METHODID_REMOVE_TRIP = 3;
  private static final int METHODID_UPDATE_TRIP = 4;
  private static final int METHODID_GET_COUNT_TRIPS_IN_LOCATION = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TripsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TripsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TRIP:
          serviceImpl.addTrip((trips.TripsOuterClass.Trip) request,
              (io.grpc.stub.StreamObserver<trips.TripsOuterClass.TripID>) responseObserver);
          break;
        case METHODID_GET_LOCATION_BY_ID:
          serviceImpl.getLocationById((trips.TripsOuterClass.LocationID) request,
              (io.grpc.stub.StreamObserver<trips.TripsOuterClass.Location>) responseObserver);
          break;
        case METHODID_GET_LOCATION_BY_CITY:
          serviceImpl.getLocationByCity((trips.TripsOuterClass.City) request,
              (io.grpc.stub.StreamObserver<trips.TripsOuterClass.LocationID>) responseObserver);
          break;
        case METHODID_REMOVE_TRIP:
          serviceImpl.removeTrip((trips.TripsOuterClass.TripID) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_TRIP:
          serviceImpl.updateTrip((trips.TripsOuterClass.UpdateTripRequest) request,
              (io.grpc.stub.StreamObserver<trips.TripsOuterClass.Trip>) responseObserver);
          break;
        case METHODID_GET_COUNT_TRIPS_IN_LOCATION:
          serviceImpl.getCountTripsInLocation((trips.TripsOuterClass.GetCountTripsInLocationRequest) request,
              (io.grpc.stub.StreamObserver<trips.TripsOuterClass.GetCountTripsInLocationResponse>) responseObserver);
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

  private static abstract class TripsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TripsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return trips.TripsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Trips");
    }
  }

  private static final class TripsFileDescriptorSupplier
      extends TripsBaseDescriptorSupplier {
    TripsFileDescriptorSupplier() {}
  }

  private static final class TripsMethodDescriptorSupplier
      extends TripsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TripsMethodDescriptorSupplier(String methodName) {
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
      synchronized (TripsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TripsFileDescriptorSupplier())
              .addMethod(getAddTripMethod())
              .addMethod(getGetLocationByIdMethod())
              .addMethod(getGetLocationByCityMethod())
              .addMethod(getRemoveTripMethod())
              .addMethod(getUpdateTripMethod())
              .addMethod(getGetCountTripsInLocationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
