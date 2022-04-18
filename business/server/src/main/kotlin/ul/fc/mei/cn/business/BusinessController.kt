package ul.fc.mei.cn.business

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ul.fc.mei.cn.business.common.Links
import ul.fc.mei.cn.business.model.Business

@RestController

class BusinessController(val service: BusinessService) {

    @GetMapping(Links.BusinessById)
    suspend fun getBusiness(@PathVariable bid: Long): ResponseEntity<Business> {
        val business = service.getBusiness(bid)
        return ResponseEntity.ok(business)
    }

    @GetMapping(Links.Businesses)
    suspend fun getBusinesses(
        @RequestParam longitude: Double,
        @RequestParam latitude: Double,
        @RequestParam radius: Double,
        @RequestParam limit: Int,
        @RequestParam skip: Int
    ): ResponseEntity<List<Business>> {
        val businesses = service.searchBusinesses(latitude, longitude, radius, limit, skip)
        return ResponseEntity.ok(businesses)
    }

    @DeleteMapping(Links.BusinessById)
    suspend fun deleteBusiness(@PathVariable bid: Long): ResponseEntity<Nothing> {
        service.deleteBusiness(bid)
        return ResponseEntity.ok().build()
    }

    @PutMapping(Links.BusinessById)
    suspend fun updateBusiness(
        @PathVariable bid: Long,
        @RequestBody newBusiness: Business
    ): ResponseEntity<Business> {
        val business = service.updateBusiness(bid, newBusiness)
        return ResponseEntity.ok(business)
    }
}