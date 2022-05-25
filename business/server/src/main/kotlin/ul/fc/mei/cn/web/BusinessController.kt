package ul.fc.mei.cn.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ul.fc.mei.cn.core.BusinessService
import ul.fc.mei.cn.core.common.Links
import ul.fc.mei.cn.core.model.Business

@RestController
class BusinessController(val service: BusinessService) {

    @GetMapping(Links.BusinessById)
    fun getBusiness(@PathVariable bid: String): ResponseEntity<Business> {
        val business = service.getBusiness(bid)
        return ResponseEntity.ok(business)
    }

    @GetMapping(Links.Businesses)
    fun getBusinesses(
        @RequestParam longitude: Double,
        @RequestParam latitude: Double,
        @RequestParam radius: Int,
        @RequestParam limit: Int,
        @RequestParam skip: Int
    ): ResponseEntity<List<Business>> {
        val businesses = service.searchBusinesses(latitude, longitude, radius, limit, skip)
        return ResponseEntity.ok(businesses)
    }

    @DeleteMapping(Links.BusinessById)
    fun deleteBusiness(@PathVariable bid: String): ResponseEntity<Nothing> {
        service.deleteBusiness(bid)
        return ResponseEntity.ok().build()
    }

    @PutMapping(Links.BusinessById)
    fun updateBusiness(
        @PathVariable bid: String,
        @RequestBody newBusiness: Business
    ): ResponseEntity<Business> {
        val business = service.updateBusiness(bid, newBusiness)
        return ResponseEntity.ok(business)
    }

    @GetMapping(Links.LoadBusinesses)
    fun loadBusinesses(
        @RequestParam longitude: Double,
        @RequestParam latitude: Double,
        @RequestParam radius: Int,
        @RequestParam limit: Int,
        @RequestParam skip: Int = 0,
    ): ResponseEntity<Void> {
        service.loadBusinessesIntoDatabase(longitude, latitude, radius, limit, skip)
        return ResponseEntity.ok().build()
    }
}