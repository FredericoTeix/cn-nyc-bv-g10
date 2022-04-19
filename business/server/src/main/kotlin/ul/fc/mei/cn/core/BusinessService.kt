package ul.fc.mei.cn.core

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.model.BusinessFeature
import ul.fc.mei.cn.core.model.DBBusiness
import ul.fc.mei.cn.core.model.ModelFeatureArray
import ul.fc.mei.cn.core.model.WebClientGetter
import ul.fc.mei.cn.core.model.toBusiness
import ul.fc.mei.cn.core.repository.BusinessRepository
import ul.fc.mei.cn.web.utils.BadRequestException
import java.lang.RuntimeException

@Service
class BusinessService(val businessRepository: BusinessRepository, val getter: WebClientGetter) {


    suspend fun updateBusiness(businessId: String, business: Business): Business {
        validateBusinessInput(business)
        val updatedBusiness = businessRepository.updateBusiness(business)
        return updatedBusiness
    }

    suspend fun deleteBusiness(businessId: String) {
        businessRepository.deleteBusiness(businessId)
    }

    suspend fun getBusiness(businessId: String) = businessRepository.getBusiness(businessId)

    suspend fun searchBusinesses(
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
        //TODO: make some validation if the coordinates are in New York
        if (business.address.isBlank()) {
            throw BadRequestException("Name or Address were cannot be blank")
        }

        if (!business.city.contains("New York", true)) {
            return false
        }
        return true
    }

    suspend fun loadBusinessesIntoDatabase(longitude: Double, latitude: Double, radius: Int, limit: Int, skip: Int) {
        getter(longitude, latitude, radius, limit, skip).get().awaitExchange { resp ->
            when (resp.statusCode()) {
                HttpStatus.BAD_REQUEST -> throw BadRequestException(resp.statusCode().reasonPhrase)
                HttpStatus.OK -> addBusinessesToDB(resp.awaitBody<ModelFeatureArray<BusinessFeature>>())
                else -> throw RuntimeException()
            }
        }
    }

    private suspend fun addBusinessesToDB(featureArray: ModelFeatureArray<BusinessFeature>) {
        featureArray.features.map {
            it.toBusiness()
        }.filter {
            validateBusinessInput(it)
        }.forEach {
            businessRepository.addBusiness(it)
        }
    }
}

