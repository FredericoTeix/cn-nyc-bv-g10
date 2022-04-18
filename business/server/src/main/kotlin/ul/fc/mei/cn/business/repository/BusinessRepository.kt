package ul.fc.mei.cn.business.repository

import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel

interface BusinessRepository {

    suspend fun getBusiness(bid: Long): Business

    suspend fun deleteBusiness(bid: Long)

    suspend fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business>

    suspend fun updateBusiness(business: Business): Business

    suspend fun addBusiness(business: Business): Boolean
}