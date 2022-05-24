package ul.fc.mei.cn.core

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import ul.fc.mei.cn.core.common.BadRequestException
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.model.BusinessFeature
import ul.fc.mei.cn.core.model.ModelFeatureArray
import ul.fc.mei.cn.core.model.ResourceGetter
import ul.fc.mei.cn.core.model.toBusiness
import ul.fc.mei.cn.core.repository.BusinessRepository

@Service
class BusinessService(
    val businessRepository: BusinessRepository,
    val getter: ResourceGetter<ModelFeatureArray<BusinessFeature>>
) {

    fun updateBusiness(businessId: String, business: Business): Business {
        validateBusinessInput(business)
        val updatedBusiness = businessRepository.updateBusiness(business)
        return updatedBusiness
    }

    fun deleteBusiness(businessId: String) {
        businessRepository.deleteBusiness(businessId)
    }

    fun getBusiness(businessId: String) = businessRepository.getBusiness(businessId)

    fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Int,
        limit: Int,
        skip: Int
    ): List<Business> {
        if (radius <= 0) {
            throw BadRequestException("Invalid radius")
        }

        if (latitude !in -90.0..90.0) {
            throw BadRequestException("Invalid latitude")
        }

        if (longitude !in -180.0..180.0) {
            throw BadRequestException("Invalid longitude")

        }
        val matchingBusinesses = businessRepository.searchBusinesses(latitude, longitude, radius, limit, skip)
        return matchingBusinesses
    }

    private fun validateBusinessInput(business: Business): Boolean {
        if (business.latitude !in -90.0..90.0 || business.longitude !in -180.0..180.0) {
            throw BadRequestException("Invalid Coordinates supplied")
        }

        if (business.address.isBlank()) {
            throw BadRequestException("Address cannot be blank")
        }

        if (!business.city.contains("New York", true)) {
            return false
        }
        return true
    }

    fun loadBusinessesIntoDatabase(longitude: Double, latitude: Double, radius: Int, limit: Int, skip: Int) {
        val resp = getter(longitude, latitude, radius, limit, skip)
        when (resp.statusCode) {
            HttpStatus.BAD_REQUEST -> throw BadRequestException(resp.statusCode.reasonPhrase)
            HttpStatus.OK -> addBusinessesToDB(resp.body!!)
            else -> throw RuntimeException()
        }
    }


    private fun addBusinessesToDB(featureArray: ModelFeatureArray<BusinessFeature>) {
        featureArray.features.map {
            it.toBusiness()
        }.filter {
            validateBusinessInput(it)
        }.forEach {
            businessRepository.addBusiness(it)
        }
    }
}

