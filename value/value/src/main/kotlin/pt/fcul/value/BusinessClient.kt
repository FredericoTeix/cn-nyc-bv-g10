package pt.fcul.value

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import ul.fc.cn.proto.BusinessOuterClass.*
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.searchBusinessRequest
import java.io.Closeable
import java.util.concurrent.TimeUnit

class BusinessClient(address: String, port: Int) : Closeable {

    private val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .build()

    private val stub = BusinessServiceGrpcKt.BusinessServiceCoroutineStub(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    suspend fun getBusiness(businessId: Long) : Business {
        // rpc getBusiness(BusinessId) returns (Business);
        val request = businessId {
            this.id = businessId
        }

        print("[getBusiness] id:{$businessId}")
        return stub.getBusiness(request)
    }

    suspend fun searchBusiness(lat : Double, lon : Double, radius : Double, limit : Int, skip : Int) : SearchBusinessResponse {
        // rpc searchBusiness(SearchBusinessRequest) returns (SearchBusinessResponse);
        val request = searchBusinessRequest {
            this.latitude = lat
            this.longitude = lon
            this.radius = radius
            this.limit = limit
            this.skip = skip
        }

        print("[searchBusiness] lat:{$lat} lon:{$lon} rad:{$radius} limit:{$limit} skip:{$skip}")
        return stub.searchBusiness(request)
    }

}