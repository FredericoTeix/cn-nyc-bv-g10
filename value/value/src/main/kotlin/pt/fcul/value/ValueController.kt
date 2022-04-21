package pt.fcul.value

import java.time.LocalDateTime
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

const val RESULTS_COUNT = 100

@RestController
class ValueController(
    val service: ValueService
) {

    @GetMapping("/value/business/{id}")
    suspend fun valueByBusiness(
        @PathVariable id: Long,
        @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ) = service.getBusinessValue(id, start, end)
//    ) = BusinessValue(1, 1)

    @GetMapping("/value/top/businesses")
    suspend fun topValueBusinesses(
        @RequestParam("latitude") lat: Double,
        @RequestParam("longitude") lon: Double,
        @RequestParam("rad") radius: Double,
        @RequestParam("num_results") n: Int?,
        @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ) = service.getTopBusiness(lat, lon, radius, n ?: RESULTS_COUNT, start, end)
//    ) = TopBusinessesAtLocation(
//        0.0, 1.0, 2.0, listOf(
//            BusinessValue(0, 0), BusinessValue(1, 1), BusinessValue(2, 2)
//        )
//    )

    @GetMapping("/value/location/radius")
    suspend fun valueByLocationRadius(
        @RequestParam("latitude") lat: Double,
        @RequestParam("longitude") lon: Double,
        @RequestParam("rad") radius: Double,
        @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ) = service.getLocationValue(lat, lon, radius, start, end)
//    ) = AreaValue(0, setOf("0", "1", "2"))

}