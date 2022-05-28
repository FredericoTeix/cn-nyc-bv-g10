package pt.fcul.value.trip

import java.time.LocalDateTime
import pt.fcul.value.Location

interface TripClient {
    // TODO return should be nullable
    suspend fun getLocationById(locationId: String): Location
    suspend fun getLocationByCity(cName: String): String
    suspend fun getCountTripsInLocation(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        locationId: String
    ): Long
}