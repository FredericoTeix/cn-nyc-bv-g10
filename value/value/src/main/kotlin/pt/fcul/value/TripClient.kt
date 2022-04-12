package pt.fcul.value

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import trips.TripsGrpcKt
import trips.TripsOuterClass.*
import trips.locationID
import java.io.Closeable
import java.util.concurrent.TimeUnit
import com.google.protobuf.Timestamp
import trips.getCountTripsInLocationRequest

class TripClient(address: String, port: Int) : Closeable {

    private val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .build()

    private val stub = TripsGrpcKt.TripsCoroutineStub(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    suspend fun getLocationById(locationId: String) : Location {
        // Find location description by ID
        // rpc GetLocationById(LocationID) returns (Location) {}
        val request = locationID {
            this.locationId = locationId
        }

        print("[getLocationById] locationID:{$locationId}")
        return stub.getLocationById(request)
    }

    suspend fun getCountTripsInLocation(startDate: Timestamp, endDate: Timestamp,
                                        locationId: String) : GetCountTripsInLocationResponse {
        // rpc GetCountTripsInLocation(GetCountTripsInLocationRequest) returns (GetCountTripsInLocationResponse) {}
        val request = getCountTripsInLocationRequest {
            this.startDate = startDate
            this.endDate = endDate
            this.locationId = locationId
        }

        print("[getCountTripsInLocation] locationID:{$locationId} startDate:{$startDate} endDate:{$endDate}")
        return stub.getCountTripsInLocation(request)
    }


}