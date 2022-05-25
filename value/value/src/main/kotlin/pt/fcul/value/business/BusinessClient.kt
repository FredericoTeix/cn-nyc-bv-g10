package pt.fcul.value.business

import pt.fcul.value.Business

interface BusinessClient {
    suspend fun getBusiness(businessId: String): Business // ### TODO can be null. change to nullable
    suspend fun searchBusiness(
        lat: Double,
        lon: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business>
}