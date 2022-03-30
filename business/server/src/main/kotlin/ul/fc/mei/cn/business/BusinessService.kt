package ul.fc.mei.cn.business

import org.springframework.stereotype.Service
import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel
import ul.fc.mei.cn.business.repository.BusinessRepository
import ul.fc.mei.cn.business.utils.BadRequestException

@Service
class BusinessService(val businessRepository: BusinessRepository) {
    fun addBusiness(business: BusinessInputModel): Int {
        validateBusinessInput(business)
        val businessId = businessRepository.createBusiness(business)
        return businessId
    }

    fun updateBusiness(businessId: Int, businessInput: BusinessInputModel): Business {
        validateBusinessInput(businessInput)
        val updatedBusiness = businessRepository.updateBusiness(businessInput.toBusiness(businessId))
        return updatedBusiness
    }

    fun deleteBusiness(businessId: Int) {
        businessRepository.deleteBusiness(businessId)
    }

    fun getBusiness(businessId: Int) = businessRepository.getBusiness(businessId)

    fun searchBusinesses(latitude: Double, longitude: Double, radius: Double, limit: Int, skip: Int): List<Business> {
        if (radius >= 0.0 || latitude !in -90.0..90.0 || longitude !in -180.0..180.0) {
            throw BadRequestException()
        }
        val matchingBusinesses = businessRepository.searchBusinesses(latitude, longitude, radius, limit, skip)
        return matchingBusinesses
    }

    private fun validateBusinessInput(business: BusinessInputModel) {
        //TODO: make some validation if the coordinates are in New York
        if (business.address.isBlank() || business.address.isBlank()) {
            throw BadRequestException()
        }
    }
}

