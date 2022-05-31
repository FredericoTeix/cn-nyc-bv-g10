package pt.fcul.value.trip

import java.time.LocalDateTime
import pt.fcul.value.Location

interface TripClient {
    // TODO return should be nullable
    fun getLocationById(locationId: String): Location
    fun getLocationByCity(cName: String): String
    fun getCountTripsInLocation(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        locationId: String
    ): Long
}