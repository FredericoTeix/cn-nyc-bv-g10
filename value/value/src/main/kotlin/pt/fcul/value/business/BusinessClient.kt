package pt.fcul.value.business

import pt.fcul.value.Business

interface BusinessClient {
    suspend fun getBusiness(businessId: Long): Business
    suspend fun searchBusiness(
        lat: Double,
        lon: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business>
}