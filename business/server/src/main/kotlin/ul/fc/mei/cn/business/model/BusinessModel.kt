package ul.fc.mei.cn.business.model

import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessId
import kotlin.math.pow

data class Business(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)

data class BusinessInputModel(
    val name: String,
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toBusiness(id: Long): Business {
        return Business(id, name, address, city, latitude, longitude)
    }
}

fun Business.isInRadius(longitude: Double, latitude: Double, radius: Double): Boolean {
    return ((this.longitude - longitude).pow(2.0) + (this.latitude - latitude).pow(2.0)) <= radius.pow(
        2.0
    )
}

fun Business.toProtobuf() : BusinessOuterClass.Business {
    return business {
        businessId { id = this.id }
        name = this.name
        address = this.address
        city = this.city
        longitude = this.longitude
        latitude = this.latitude
    }
}