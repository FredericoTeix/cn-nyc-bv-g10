package ul.fc.mei.cn.business.model

import kotlin.math.pow

data class Business (val id: Int, val name: String, val address: String, val latitude: Double, val longitude: Double)

data class BusinessInputModel (val name: String, val address: String, val latitude: Double, val longitude: Double) {
    fun toBusiness(id: Int): Business {
        return Business(id, name, address, latitude, longitude)
    }
}
fun Business.isInRadius(longitude: Double, latitude: Double, radius: Double): Boolean {
    return ((this.longitude - longitude).pow(2.0) + (this.latitude - latitude).pow(2.0)) <= radius.pow(
        2.0
    )
}