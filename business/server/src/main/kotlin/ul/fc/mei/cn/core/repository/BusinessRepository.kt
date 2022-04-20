package ul.fc.mei.cn.core.repository

import ul.fc.mei.cn.core.model.Business

interface BusinessRepository {

    suspend fun getBusiness(bid: String): Business

    suspend fun deleteBusiness(bid: String)

    suspend fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Int,
        limit: Int,
        skip: Int
    ): List<Business>

    suspend fun updateBusiness(business: Business): Business

    suspend fun addBusiness(business: Business): Boolean
}