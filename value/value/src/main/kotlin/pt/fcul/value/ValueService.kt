package pt.fcul.value

import java.time.LocalDateTime
import org.springframework.stereotype.Service
import pt.fcul.value.business.BusinessClient
import pt.fcul.value.trip.TripClient

@Service
class ValueService(
    val businessClient: BusinessClient,
    val tripClient: TripClient,
) {

    /**
     * Get the value associated to a business in a given time range
     */
    fun getBusinessValue(
        id: String,
        start: LocalDateTime,
        end: LocalDateTime
    ): BusinessValue {
        val business = businessClient.getBusiness(id)
        val locationId = tripClient.getLocationByCity(business.city)
        // TODO what if they are null ? response should be a 404
        //   you can do this by returning null to the ValueController and create there the 404 response
        //   or you can throw an exception, and let an ExceptionHandler convert it to a 404 response, as done in keys service


        println("${id}: $start $end")
        val value = tripClient.getCountTripsInLocation(start, end, locationId)

        return BusinessValue(id.toString(), value)
    }

    /**
     *  Get the top valued businesses in a given time range, regarding some location
     */
    fun getTopBusiness(
        lat: Double,
        lon: Double,
        radius: Double,
        resultsCount: Int,
        start: LocalDateTime,
        end: LocalDateTime
    ): TopBusinessesAtLocation {
        // given a (lat,lon), #resultsCount are enough to calculate top businesses
        val b = businessClient.searchBusiness(lat, lon, radius, resultsCount, 0)

        // TODO map will always be empty ?
        val map = HashMap<Int, Long>(b.size) // key: b[i] value: BusinessValue
        for (i in 0..b.size) { // Iterate over response results
            val r = b[i]

            var founded = false
            for (index in map.keys) {
                val business = b[index]
                if (r.city == business.city) {
                    map[i] = map.getOrDefault(index, -1) // If anything goes wrong business value is < 0
                    founded = true
                    break
                }
            }

            if (!founded) { // r[i] business belongs to a new city
                val id = tripClient.getLocationByCity(r.city)
                val value = tripClient.getCountTripsInLocation(start, end, id)
                map[i] = value
            }
        }

        val sortedList = map
            .toList()
            .sortedBy { (_, value) -> value }
            .map { (idx, value) -> BusinessValue(b[idx].id, value) }

        return TopBusinessesAtLocation(lat, lon, radius, sortedList)
    }

    fun getLocationValue(
        lat: Double,
        lon: Double,
        radius: Double,
        start: LocalDateTime,
        end: LocalDateTime
    ): AreaValue {
        // given a (lat,lon), RESULTS_COUNT are enough to calculate top businesses
        val b = businessClient.searchBusiness(lat, lon, radius, RESULTS_COUNT, 0)

        var value: Long = 0
        val citiesMutableSet = mutableSetOf<String>() // List of cities analysed

        for (i in 0..b.size) { // Iterate over response results
            val r = b[i]
            if (citiesMutableSet.add(r.city)) {
                val id = tripClient.getLocationByCity(r.city)
                value += tripClient.getCountTripsInLocation(start, end, id)
            }
        }

        return AreaValue(value, citiesMutableSet.toSet())
    }

}