package ul.fc.mei.cn.business.repository

import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.isInRadius
import ul.fc.mei.cn.business.utils.NotFoundException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong


class BusinessMemoryRepository : BusinessRepository {

    private val businessMap = ConcurrentHashMap<Long, Business>()
    private val atomicLong = AtomicLong(1)

    override suspend fun getBusiness(bid: Long): Business {
        return if (businessMap.containsKey(bid)) {
            businessMap[bid]!!
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override suspend fun deleteBusiness(bid: Long) {
        if (businessMap.containsKey(bid)) {
            businessMap.remove(bid)
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override suspend fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business> {
        return businessMap.values.filter {
            it.isInRadius(latitude, longitude, radius)
        }.drop(skip).take(limit)
    }

    override suspend fun updateBusiness(business: Business): Business {
        if (businessMap.containsKey(business.id)) {
            businessMap[business.id] = business
            return business
        } else throw  NotFoundException("There isn't a Business with Id: ${business.id}")
    }

    override suspend fun addBusiness(business: Business): Boolean {
        val id = atomicLong.incrementAndGet()
        businessMap[id] = business
        return true
    }

}