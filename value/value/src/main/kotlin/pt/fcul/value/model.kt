package pt.fcul.value

import kotlinx.serialization.Serializable

/**
 * Data models
 */

@Serializable
data class BusinessValue(val id: String, val value: Long)

@Serializable
data class TopBusinessesAtLocation(
    val lat: Double,
    val lon: Double,
    val radius: Double,
    val businesses: List<BusinessValue>
)

@Serializable
data class AreaValue(val value: Long, val cities: Set<String>)

data class Location(
    val zone: String,
    val borough: String,
    val locationId: Int,
    val serviceZone: String,
)

data class Business(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
)