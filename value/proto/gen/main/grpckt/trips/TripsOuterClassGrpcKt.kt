package trips

import com.google.protobuf.Empty
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import trips.TripsGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for trips.Trips.
 */
object TripsGrpcKt {
  const val SERVICE_NAME: String = TripsGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = TripsGrpc.getServiceDescriptor()

  val addTripMethod: MethodDescriptor<TripsOuterClass.Trip, TripsOuterClass.TripID>
    @JvmStatic
    get() = TripsGrpc.getAddTripMethod()

  val getLocationByIdMethod: MethodDescriptor<TripsOuterClass.LocationID, TripsOuterClass.Location>
    @JvmStatic
    get() = TripsGrpc.getGetLocationByIdMethod()

  val getLocationByCityMethod: MethodDescriptor<TripsOuterClass.City, TripsOuterClass.LocationID>
    @JvmStatic
    get() = TripsGrpc.getGetLocationByCityMethod()

  val removeTripMethod: MethodDescriptor<TripsOuterClass.TripID, Empty>
    @JvmStatic
    get() = TripsGrpc.getRemoveTripMethod()

  val updateTripMethod: MethodDescriptor<TripsOuterClass.UpdateTripRequest, TripsOuterClass.Trip>
    @JvmStatic
    get() = TripsGrpc.getUpdateTripMethod()

  val getCountTripsInLocationMethod:
      MethodDescriptor<TripsOuterClass.GetCountTripsInLocationRequest,
      TripsOuterClass.GetCountTripsInLocationResponse>
    @JvmStatic
    get() = TripsGrpc.getGetCountTripsInLocationMethod()

  /**
   * A stub for issuing RPCs to a(n) trips.Trips service as suspending coroutines.
   */
  @StubFor(TripsGrpc::class)
  class TripsCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<TripsCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): TripsCoroutineStub =
        TripsCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun addTrip(request: TripsOuterClass.Trip, headers: Metadata = Metadata()):
        TripsOuterClass.TripID = unaryRpc(
      channel,
      TripsGrpc.getAddTripMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getLocationById(request: TripsOuterClass.LocationID, headers: Metadata =
        Metadata()): TripsOuterClass.Location = unaryRpc(
      channel,
      TripsGrpc.getGetLocationByIdMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getLocationByCity(request: TripsOuterClass.City, headers: Metadata = Metadata()):
        TripsOuterClass.LocationID = unaryRpc(
      channel,
      TripsGrpc.getGetLocationByCityMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun removeTrip(request: TripsOuterClass.TripID, headers: Metadata = Metadata()): Empty =
        unaryRpc(
      channel,
      TripsGrpc.getRemoveTripMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun updateTrip(request: TripsOuterClass.UpdateTripRequest, headers: Metadata =
        Metadata()): TripsOuterClass.Trip = unaryRpc(
      channel,
      TripsGrpc.getUpdateTripMethod(),
      request,
      callOptions,
      headers
    )
    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    suspend fun getCountTripsInLocation(request: TripsOuterClass.GetCountTripsInLocationRequest,
        headers: Metadata = Metadata()): TripsOuterClass.GetCountTripsInLocationResponse = unaryRpc(
      channel,
      TripsGrpc.getGetCountTripsInLocationMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the trips.Trips service based on Kotlin coroutines.
   */
  abstract class TripsCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for trips.Trips.AddTrip.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addTrip(request: TripsOuterClass.Trip): TripsOuterClass.TripID = throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.AddTrip is unimplemented"))

    /**
     * Returns the response to an RPC for trips.Trips.GetLocationById.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getLocationById(request: TripsOuterClass.LocationID): TripsOuterClass.Location
        = throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.GetLocationById is unimplemented"))

    /**
     * Returns the response to an RPC for trips.Trips.GetLocationByCity.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getLocationByCity(request: TripsOuterClass.City): TripsOuterClass.LocationID =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.GetLocationByCity is unimplemented"))

    /**
     * Returns the response to an RPC for trips.Trips.RemoveTrip.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun removeTrip(request: TripsOuterClass.TripID): Empty = throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.RemoveTrip is unimplemented"))

    /**
     * Returns the response to an RPC for trips.Trips.UpdateTrip.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun updateTrip(request: TripsOuterClass.UpdateTripRequest): TripsOuterClass.Trip =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.UpdateTrip is unimplemented"))

    /**
     * Returns the response to an RPC for trips.Trips.GetCountTripsInLocation.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend
        fun getCountTripsInLocation(request: TripsOuterClass.GetCountTripsInLocationRequest):
        TripsOuterClass.GetCountTripsInLocationResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method trips.Trips.GetCountTripsInLocation is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getAddTripMethod(),
      implementation = ::addTrip
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getGetLocationByIdMethod(),
      implementation = ::getLocationById
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getGetLocationByCityMethod(),
      implementation = ::getLocationByCity
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getRemoveTripMethod(),
      implementation = ::removeTrip
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getUpdateTripMethod(),
      implementation = ::updateTrip
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TripsGrpc.getGetCountTripsInLocationMethod(),
      implementation = ::getCountTripsInLocation
    )).build()
  }
}
