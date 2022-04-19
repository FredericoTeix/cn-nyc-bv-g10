package pt.fcul.value

import com.google.protobuf.Timestamp
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ValueController {

    // TODO: [WARNING] Services ports may change later on
    private val businessClient = BusinessClient(System.getenv("BUSINESS"),8080)
    private val tripClient = TripClient(System.getenv("TRIP"),50051)

    @GetMapping("/value/business/{id}")
    suspend fun valueByBusiness(@PathVariable id:Long,
                                @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: Timestamp,
                                @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: Timestamp
    ) : Long
    {
        //TODO: Get the value associated to a business in a given time range
        //  (1) Get business Location (Serviço do daniel)
        //  (2) Count moves at Business Location from start_date until end_date (Serviço Rodrigo) and Return that value
        val b = businessClient.getBusiness(id)
        val loc = tripClient.getLocationByCity(b.city)

        println("${id}: $start $end")
        return tripClient.getCountTripsInLocation(start,end,loc.locationId.toString()).count
    }

    @GetMapping("/value/top")
    suspend fun topValueBusinesses( @RequestParam("latitude") lat: Double,
                            @RequestParam("longitude") lon: Double,
                            @RequestParam("rad") radius: Double,
                            @RequestParam("num_results") n: Int,
                            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: Timestamp,
                            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: Timestamp
    ) : String
    {
        val nResults = 100 // given a (lat,lon), nResults are enough to calculate top businesses
        val b = businessClient.searchBusiness(lat,lon,radius,nResults,0)

        val map = HashMap<Int,Long>(b.resultsCount) // key: b.getResult(i) value: BusinessValue
        for(i in 0..b.resultsCount) { // Iterate over response results
            val r = b.getResults(i)

            var founded = false
            for (index in map.keys){
                val business = b.getResults(index)
                if (r.city.equals(business.city)) {
                    map[i] = map.getOrDefault(index,-1) // If anything goes wrong business value is < 0
                    founded = true
                    break;
                }
            }
            if (!founded) { // r[i] business belongs to a new city
                val id = tripClient.getLocationByCity(r.city).locationId
                val value = tripClient.getCountTripsInLocation(start, end, id).count
                map[i] = value
            }
        }

        return map.toList().sortedBy { (_,value) -> value }.toString()
    }

    @GetMapping("/value/location/radius")
    suspend fun valueByLocationRadius( @RequestParam("latitude") lat: Double,
                               @RequestParam("longitude") lon: Double,
                               @RequestParam("rad") radius: Double,
                               @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: Timestamp,
                               @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: Timestamp
    ) : Long
    {
        // TODO: Get the value associated to a location in a given time range defining
        //  the search location by a coordinate and a radius
        val nResults = 100 // given a (lat,lon), nResults are enough to calculate top businesses
        val b = businessClient.searchBusiness(lat,lon,radius,nResults,0)
        var areaValue:Long = 0
        val citiesMutableSet = mutableSetOf<String>() // List of cities analysed
        for(i in 0..b.resultsCount) { // Iterate over response results
            val r = b.getResults(i)
            if (citiesMutableSet.add(r.city)) {
                val id = tripClient.getLocationByCity(r.city).locationId
                areaValue += tripClient.getCountTripsInLocation(start, end, id).count
            }
        }
        return areaValue
    }


}