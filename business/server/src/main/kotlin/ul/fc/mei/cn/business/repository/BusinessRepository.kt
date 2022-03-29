package ul.fc.mei.cn.business.repository

import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel

interface BusinessRepository {

    fun getBusiness(bid: Int): Business

    fun deleteBusiness(bid: Int)

    fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business>

    fun updateBusiness(business: Business): Business

    fun createBusiness(business: BusinessInputModel): Int
}