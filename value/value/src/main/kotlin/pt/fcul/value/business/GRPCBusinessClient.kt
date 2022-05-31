package pt.fcul.value.business

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.util.concurrent.TimeUnit
import pt.fcul.value.Business
import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.BusinessServiceGrpc
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.searchBusinessRequest

class GRPCBusinessClient(address: String, port: Int) : BusinessClient, Closeable {

    private val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .build()

    private val stub = BusinessServiceGrpc.newBlockingStub(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    override fun getBusiness(businessId: String): Business {
        // rpc getBusiness(BusinessId) returns (Business);
        val request = businessId {
            this.id = businessId
        }

        print("[getBusiness] id:{$businessId}")
        return stub.getBusiness(request).dto()
    }

    override fun searchBusiness(
        lat: Double,
        lon: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business> {
        // rpc searchBusiness(SearchBusinessRequest) returns (SearchBusinessResponse);
        val request = searchBusinessRequest {
            this.latitude = lat
            this.longitude = lon
            this.radius = radius.toInt()
            this.limit = limit
            this.skip = skip
        }

        val response = stub.searchBusiness(request)
        if(response.hasError()) return listOf<Business>()

        print("[searchBusiness] lat:{$lat} lon:{$lon} rad:{$radius} limit:{$limit} skip:{$skip}")
        return response.results.resultsList.map{it.dto()}
    }

}

fun BusinessOuterClass.Business.dto() = Business(id.id, name, address, city, latitude, longitude)