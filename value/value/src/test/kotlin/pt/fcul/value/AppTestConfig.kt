package pt.fcul.value

import java.time.LocalDateTime
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import pt.fcul.value.business.BusinessClient
import pt.fcul.value.trip.TripClient

@TestConfiguration
@Profile("mocked-clients-config")
class AppTestConfig {

    @Bean
    fun getBusinessClient(): BusinessClient = MockBusinessClient()

    @Bean
    fun getTripClient(): TripClient = MockTripClient()

}

class MockBusinessClient : BusinessClient {
    private val businesses = mapOf(
        "0" to Business("0", "b0", "a0", "c0", 0.0, 0.0),
        "1" to Business("1", "b1", "a1", "c1", 1.0, 1.0),
    )

    override suspend fun getBusiness(businessId: String): Business {
        // TODO can be null. maybe needs to change BusinessClient to return nullable Business
        return businesses[businessId]!!
    }

    override suspend fun searchBusiness(
        lat: Double,
        lon: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business> {
        return listOf()
    }
}

class MockTripClient : TripClient {
    private val locations = mapOf(
        "0" to Location("z0", "b0", 0, "sz0"),
        "1" to Location("z1", "b1", 1, "sz1")
    )

    private val tripsCount = mapOf(
        "0" to 0L,
        "1" to 1L
    )

    override suspend fun getLocationById(locationId: String): Location {
        return locations[locationId]!!
    }

    override suspend fun getLocationByCity(cName: String): String {
        // shouldn't all locations be in new york city ?
        return locations.values.find { loc ->
            loc.zone.equals(cName, ignoreCase = true)
        }?.locationId?.toString() ?: ""
    }

    override suspend fun getCountTripsInLocation(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        locationId: String
    ): Long {
        return tripsCount[locationId] ?: 0L
    }
}
