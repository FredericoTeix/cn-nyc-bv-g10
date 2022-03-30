package ul.fc.mei.cn.business

import org.springframework.stereotype.Service
import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel
import ul.fc.mei.cn.business.repository.BusinessRepository
import ul.fc.mei.cn.business.utils.BadRequestException

@Service
class BusinessService(val businessRepository: BusinessRepository) {
    suspend fun addBusiness(business: BusinessInputModel): Long {
        validateBusinessInput(business)
        val businessId = businessRepository.createBusiness(business)
        return businessId
    }

    suspend fun updateBusiness(businessId: Long, businessInput: BusinessInputModel): Business {
        validateBusinessInput(businessInput)
        val updatedBusiness = businessRepository.updateBusiness(businessInput.toBusiness(businessId))
        return updatedBusiness
    }

    suspend fun deleteBusiness(businessId: Long) {
        businessRepository.deleteBusiness(businessId)
    }

    suspend fun getBusiness(businessId: Long) = businessRepository.getBusiness(businessId)

    suspend fun searchBusinesses(latitude: Double, longitude: Double, radius: Double, limit: Int, skip: Int): List<Business> {
        if (radius >= 0.0) {
            throw BadRequestException("Invalid radius")
        }

        if(latitude !in -90.0..90.0) {
            throw BadRequestException("Invalid latitude")
        }

        if(longitude !in -180.0..180.0) {
            throw BadRequestException("Invalid longitude")

        }
        val matchingBusinesses = businessRepository.searchBusinesses(latitude, longitude, radius, limit, skip)
        return matchingBusinesses
    }

    private fun validateBusinessInput(business: BusinessInputModel) {
        //TODO: make some validation if the coordinates are in New York
        if (business.address.isBlank() || business.name.isBlank()) {
            throw BadRequestException("Name or Address were cannot be blank")
        }
    }
}

