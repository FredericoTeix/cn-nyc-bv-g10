package ul.fc.mei.cn.business

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel
import java.net.URI

@RestController
@RequestMapping("/business")
class BusinessController(val service: BusinessService) {

    @GetMapping("{bid}")
    fun getBusiness(@PathVariable bid: Int): ResponseEntity<Business> {
        val business = service.getBusiness(bid)
        return ResponseEntity.ok(business)
    }

    @GetMapping("/")
    fun getBusinesses(
        @RequestParam longitude: Double,
        @RequestParam latitude: Double,
        @RequestParam radius: Double,
        @RequestParam limit: Int,
        @RequestParam skip: Int
    ): ResponseEntity<List<Business>> {
        val businesses = service.searchBusinesses(latitude, longitude, radius, limit, skip)
        return ResponseEntity.ok(businesses)
    }

    @PostMapping("/")
    fun createBusiness(@RequestBody business: BusinessInputModel): ResponseEntity<Nothing> {
        val id = service.addBusiness(business)
        return ResponseEntity.created(URI("/business/$id")).build()
    }

    @DeleteMapping("/{bid}")
    fun deleteBusiness(@PathVariable bid: Int): ResponseEntity<Nothing> {
        service.deleteBusiness(bid)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{bid}")
    fun updateBusiness(@PathVariable bid: Int, @RequestBody newBusiness: BusinessInputModel): ResponseEntity<Business> {
        val business = service.updateBusiness(bid, newBusiness)
        return ResponseEntity.ok(business)
    }
}