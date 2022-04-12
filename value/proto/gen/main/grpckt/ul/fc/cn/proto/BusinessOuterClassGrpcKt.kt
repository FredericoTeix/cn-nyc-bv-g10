package ul.fc.cn.proto

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
import ul.fc.cn.proto.BusinessServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for ul.fc.cn.proto.BusinessService.
 */
object BusinessServiceGrpcKt {
  const val SERVICE_NAME: String = BusinessServiceGrpc.SERVICE_NAME

  @JvmStatic
  val serviceDescriptor: ServiceDescriptor
    get() = BusinessServiceGrpc.getServiceDescriptor()

  val addBusinessMethod: MethodDescriptor<BusinessOuterClass.Business,
      BusinessOuterClass.BusinessId>
    @JvmStatic
    get() = BusinessServiceGrpc.getAddBusinessMethod()

  val getBusinessMethod: MethodDescriptor<BusinessOuterClass.BusinessId,
      BusinessOuterClass.Business>
    @JvmStatic
    get() = BusinessServiceGrpc.getGetBusinessMethod()

  val updateBusinessMethod: MethodDescriptor<BusinessOuterClass.UpdateBusinessRequest,
      BusinessOuterClass.Business>
    @JvmStatic
    get() = BusinessServiceGrpc.getUpdateBusinessMethod()

  val deleteBusinessMethod: MethodDescriptor<BusinessOuterClass.BusinessId,
      BusinessOuterClass.DeleteBusinessResponse>
    @JvmStatic
    get() = BusinessServiceGrpc.getDeleteBusinessMethod()

  val searchBusinessMethod: MethodDescriptor<BusinessOuterClass.SearchBusinessRequest,
      BusinessOuterClass.DeleteBusinessResponse>
    @JvmStatic
    get() = BusinessServiceGrpc.getSearchBusinessMethod()

  /**
   * A stub for issuing RPCs to a(n) ul.fc.cn.proto.BusinessService service as suspending
   * coroutines.
   */
  @StubFor(BusinessServiceGrpc::class)
  class BusinessServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT
  ) : AbstractCoroutineStub<BusinessServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): BusinessServiceCoroutineStub =
        BusinessServiceCoroutineStub(channel, callOptions)

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
    suspend fun addBusiness(request: BusinessOuterClass.Business, headers: Metadata = Metadata()):
        BusinessOuterClass.BusinessId = unaryRpc(
      channel,
      BusinessServiceGrpc.getAddBusinessMethod(),
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
    suspend fun getBusiness(request: BusinessOuterClass.BusinessId, headers: Metadata = Metadata()):
        BusinessOuterClass.Business = unaryRpc(
      channel,
      BusinessServiceGrpc.getGetBusinessMethod(),
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
    suspend fun updateBusiness(request: BusinessOuterClass.UpdateBusinessRequest, headers: Metadata
        = Metadata()): BusinessOuterClass.Business = unaryRpc(
      channel,
      BusinessServiceGrpc.getUpdateBusinessMethod(),
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
    suspend fun deleteBusiness(request: BusinessOuterClass.BusinessId, headers: Metadata =
        Metadata()): BusinessOuterClass.DeleteBusinessResponse = unaryRpc(
      channel,
      BusinessServiceGrpc.getDeleteBusinessMethod(),
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
    suspend fun searchBusiness(request: BusinessOuterClass.SearchBusinessRequest, headers: Metadata
        = Metadata()): BusinessOuterClass.DeleteBusinessResponse = unaryRpc(
      channel,
      BusinessServiceGrpc.getSearchBusinessMethod(),
      request,
      callOptions,
      headers
    )}

  /**
   * Skeletal implementation of the ul.fc.cn.proto.BusinessService service based on Kotlin
   * coroutines.
   */
  abstract class BusinessServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for ul.fc.cn.proto.BusinessService.addBusiness.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun addBusiness(request: BusinessOuterClass.Business):
        BusinessOuterClass.BusinessId = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ul.fc.cn.proto.BusinessService.addBusiness is unimplemented"))

    /**
     * Returns the response to an RPC for ul.fc.cn.proto.BusinessService.getBusiness.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun getBusiness(request: BusinessOuterClass.BusinessId):
        BusinessOuterClass.Business = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ul.fc.cn.proto.BusinessService.getBusiness is unimplemented"))

    /**
     * Returns the response to an RPC for ul.fc.cn.proto.BusinessService.updateBusiness.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun updateBusiness(request: BusinessOuterClass.UpdateBusinessRequest):
        BusinessOuterClass.Business = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ul.fc.cn.proto.BusinessService.updateBusiness is unimplemented"))

    /**
     * Returns the response to an RPC for ul.fc.cn.proto.BusinessService.deleteBusiness.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun deleteBusiness(request: BusinessOuterClass.BusinessId):
        BusinessOuterClass.DeleteBusinessResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ul.fc.cn.proto.BusinessService.deleteBusiness is unimplemented"))

    /**
     * Returns the response to an RPC for ul.fc.cn.proto.BusinessService.searchBusiness.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    open suspend fun searchBusiness(request: BusinessOuterClass.SearchBusinessRequest):
        BusinessOuterClass.DeleteBusinessResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method ul.fc.cn.proto.BusinessService.searchBusiness is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = BusinessServiceGrpc.getAddBusinessMethod(),
      implementation = ::addBusiness
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = BusinessServiceGrpc.getGetBusinessMethod(),
      implementation = ::getBusiness
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = BusinessServiceGrpc.getUpdateBusinessMethod(),
      implementation = ::updateBusiness
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = BusinessServiceGrpc.getDeleteBusinessMethod(),
      implementation = ::deleteBusiness
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = BusinessServiceGrpc.getSearchBusinessMethod(),
      implementation = ::searchBusiness
    )).build()
  }
}
