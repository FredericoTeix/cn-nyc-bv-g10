package ul.fc.mei.cn.business.repository

import org.springframework.stereotype.Repository
import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.model.BusinessInputModel
import ul.fc.mei.cn.business.model.isInRadius
import ul.fc.mei.cn.business.utils.NotFoundException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Repository
class BusinessMemoryRepository : BusinessRepository {

    private val businessMap =  ConcurrentHashMap<Int, Business>()
    private val atomicInteger = AtomicInteger(1)

    override fun getBusiness(bid: Int): Business {
        return if (businessMap.containsKey(bid)) {
            businessMap[bid]!!
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override fun deleteBusiness(bid: Int) {
        if (businessMap.containsKey(bid)) {
            businessMap.remove(bid)
        } else throw NotFoundException("There isn't a Business with Id: $bid")
    }

    override fun searchBusinesses(
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

    override fun updateBusiness(business: Business): Business {
        if (businessMap.containsKey(business.id)) {
            businessMap[business.id] = business
            return business
        } else throw  NotFoundException("There isn't a Business with Id: ${business.id}")
    }

    override fun createBusiness(business: BusinessInputModel): Int {
        val id = atomicInteger.incrementAndGet()
        businessMap[id] = Business(id, business.name, business.address, business.latitude, business.longitude)
        return id
    }

}