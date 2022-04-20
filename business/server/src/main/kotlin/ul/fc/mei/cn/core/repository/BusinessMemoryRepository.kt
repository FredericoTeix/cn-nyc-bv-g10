package ul.fc.mei.cn.core.repository

import ul.fc.mei.cn.core.common.NotFoundException
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.model.isInRadius
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong


class BusinessMemoryRepository : BusinessRepository {

    private val businessMap = ConcurrentHashMap<String, Business>()
    private val atomicLong = AtomicLong(1)


    override fun getBusiness(bid: String): Business {
        return if (businessMap.containsKey(bid)) {
            businessMap[bid]!!
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override fun deleteBusiness(bid: String) {
        if (businessMap.containsKey(bid)) {
            businessMap.remove(bid)
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Int,
        limit: Int,
        skip: Int
    ): List<Business> {
        return businessMap.values.filter {
            it.isInRadius(latitude, longitude, radius)
        }.drop(skip).take(limit)
    }

    override fun updateBusiness(business: Business): Business {
        if (businessMap.containsKey(business.id)) {
            businessMap[business.id] = business
            return business
        } else throw  NotFoundException("There isn't a Business with Id: ${business.id}")
    }

    override fun addBusiness(business: Business): Boolean {
        val id = "${atomicLong.incrementAndGet()}"
        businessMap[id] = business
        return true
    }

}