package pt.fcul.value

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import pt.fcul.value.business.BusinessClient
import pt.fcul.value.business.GRPCBusinessClient
import pt.fcul.value.trip.GRPCTripClient
import pt.fcul.value.trip.TripClient

@SpringBootApplication
class ValueApplication

fun main(args: Array<String>) {
    runApplication<ValueApplication>(*args)
}

@Configuration
@Profile("default")
class Configuration {

    // TODO: [WARNING] Services ports may change later on

    @Bean
    fun getBusinessClient(): BusinessClient {
        val address = System.getenv("BUSINESS") ?: "localhost"
        val port = System.getenv("BUSINESS_PORT")?.toIntOrNull() ?: 5001

        return GRPCBusinessClient(address, port)
    }

    @Bean
    fun getTripClient(): TripClient {
        val address = System.getenv("TRIP") ?: "localhost"
        val port = System.getenv("TRIP_PORT")?.toIntOrNull() ?: 5001

        return GRPCTripClient(address, port)
    }

}


