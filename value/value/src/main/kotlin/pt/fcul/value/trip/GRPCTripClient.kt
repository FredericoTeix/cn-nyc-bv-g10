package pt.fcul.value.trip

import com.google.protobuf.Timestamp
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.TimeUnit
import pt.fcul.value.Location
import trips.TripsGrpcKt
import trips.TripsOuterClass
import trips.city
import trips.getCountTripsInLocationRequest
import trips.locationID

class GRPCTripClient(address: String, port: Int) : TripClient, Closeable {

    private val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress(address, port)
        .usePlaintext()
        .build()

    private val stub = TripsGrpcKt.TripsCoroutineStub(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    override suspend fun getLocationById(locationId: String): Location {
        // Find location description by ID
        // rpc GetLocationById(LocationID) returns (Location) {}
        val request = locationID {
            this.locationId = locationId
        }

        println("[getLocationById] locationID:{$locationId}")
        return stub.getLocationById(request).dto()
    }

    override suspend fun getCountTripsInLocation(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        locationId: String
    ): Long {
        // rpc GetCountTripsInLocation(GetCountTripsInLocationRequest) returns (GetCountTripsInLocationResponse) {}
        val request = getCountTripsInLocationRequest {
            this.startDate = startDate.toProtoTime()
            this.endDate = endDate.toProtoTime()
            this.locationId = locationId
        }

        println("[getCountTripsInLocation] locationID:{$locationId} startDate:{$startDate} endDate:{$endDate}")
        return stub.getCountTripsInLocation(request).count
    }

    override suspend fun getLocationByCity(cName: String): String {
        //rpc GetLocationByCity(City) returns (LocationID) {}
        val request = city {
            this.cityName = cName
        }

        println("[getLocationByCity] city:{$cName}")
        return stub.getLocationByCity(request).locationId
    }

}

fun LocalDateTime.toProtoTime(): Timestamp {
    val instant = toInstant(ZoneOffset.UTC)

    return Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano)
        .build()
}

fun TripsOuterClass.Location.dto() = Location(zone, borough, locationId, serviceZone)