package ul.fc.mei.cn.core.repository

import ul.fc.mei.cn.core.model.Business

interface BusinessRepository {

    fun getBusiness(bid: String): Business

    fun deleteBusiness(bid: String)

    fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Int,
        limit: Int,
        skip: Int
    ): List<Business>

    fun updateBusiness(business: Business): Business

    fun addBusiness(business: Business): Boolean
}