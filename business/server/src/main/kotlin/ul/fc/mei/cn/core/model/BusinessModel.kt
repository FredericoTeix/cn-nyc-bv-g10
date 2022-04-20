package ul.fc.mei.cn.core.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.web.reactive.function.client.WebClient
import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessId
import kotlin.math.pow

typealias ModelBusiness = Business

data class Business(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)

fun Business.isInRadius(longitude: Double, latitude: Double, radius: Int): Boolean {
    return ((this.longitude - longitude).pow(2.0) + (this.latitude - latitude).pow(2.0)) <= (radius * radius).toDouble()
}

fun Business.toProtobuf(): BusinessOuterClass.Business {
    return business {
        businessId { id = this.id }
        name = this.name
        address = this.address
        city = this.city
        longitude = this.longitude
        latitude = this.latitude
    }
}

data class DBBusiness(
    @BsonId
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val location: Coordinates,
)

data class Coordinates(val type: String = "Point", val coordinates: List<Double>)

fun Business.toDBModel(): DBBusiness {
    return DBBusiness(
        id,
        name = name,
        address = address,
        city = city,
        location = Coordinates(coordinates = listOf(longitude, latitude))
    )
}

fun DBBusiness.toBusiness(): Business {
    return Business(
        id = id,
        name = name,
        address = address,
        city = city,
        longitude =  location.coordinates[0],
        latitude =  location.coordinates[1],
    )
}

fun BusinessFeature.toBusiness(): Business {
    return Business(
        properties.id,
        name = properties.name,
        address = properties.address,
        city = properties.city,
        longitude = geometry.coordinates[0],
        latitude = geometry.coordinates[1],
    )
}

data class ModelFeatureArray<T>(val type: String = "FeatureCollection", val features: List<T>)
data class BusinessFeature(val type: String = "Feature", val properties: BusinessProperties, val geometry: Coordinates)
data class BusinessProperties(
    @JsonProperty("place_id")
    val id: String,
    val name: String = "",
    val city: String,
    @JsonProperty("formatted")
    val address: String
)

typealias WebClientGetter = (Double, Double, Int, Int, Int) -> WebClient