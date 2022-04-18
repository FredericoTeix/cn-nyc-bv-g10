package pt.fcul.value

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ValueController {

    // TODO: [WARNING] Services ports may change later on
    private val businessClient = BusinessClient(System.getenv("BUSINESS"),8080)
    private val tripClient = TripClient(System.getenv("TRIP"),50051)

    @GetMapping("/business/{id}")
    suspend fun valueByBusiness(@PathVariable id:Long,
                                @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: Date,
                                @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: Date
    ){
        //TODO: Get the value associated to a business in a given time range
        //  (1) Get business Location (Serviço do daniel)
        //  (2) Count moves at Business Location from start_date until end_date (Serviço Rodrigo) and Return that value
        val b = businessClient.getBusiness(id)

        println("${id}: $start $end")
    }

    @GetMapping("/top")
    fun topValueBusinesses( @RequestParam("num_results") n: Int,
                            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: Date,
                            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: Date,
                            @RequestParam categories:Array<String>
    ){
        // TODO: Get the top valued businesses in a given time range
        //  (1) LocationId com mais moves in a given time range (Serviço Rodrigo)
        //  (2) Get Businesses from LocationId (Serviço Daniel) filtered by categories
        //  (3) Return random N results
    }

    /*
        // ----------- NOT AVAILABLE YET --------------
        @GetMapping
        fun valueByLocationRadius()
        {
            // TODO: Get the value associated to a location in a given time range defining
            //  the search location by a coordinate and a radius
        }

        @GetMapping
        fun valueByLocationSquare()
        {
            // TODO: Get the value associated to a location in a given time range defining
            //  the search location by two coordinates
        }

        @GetMapping
        fun valueByWalkTime()
        {
            // TODO: Get the value associated to a location in a given time range defining
            //   the search location by a coordinate and time needed to get there by foot
        }
     */
}