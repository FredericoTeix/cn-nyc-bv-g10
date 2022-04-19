package ul.fc.mei.cn

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ul.fc.mei.cn.core.common.ProblemJsonConverter
import ul.fc.mei.cn.core.model.DBBusiness
import ul.fc.mei.cn.core.model.WebClientGetter
import ul.fc.mei.cn.web.utils.EnvVarNotFoundException

@SpringBootApplication
class BusinessApplication

private const val MONGO_ENV_VAR = "MONGO_DATABASE_URL"
private const val MONGO_DB_VAR = "MONGO_DATABASE_NAME"
private const val MONGO_DB_COLLECTION_VAR = "MONGO_COLLECTION_NAME"
private const val  PLACES_API_ENDPOINT_VAR = "PLACES_ENDPOINT"
private const val  PLACES_API_KEY_VAR = "PLACES_API_KEY"


@Configuration
class WebConfiguration : WebMvcConfigurer {

    private val log = LoggerFactory.getLogger(WebConfiguration::class.java)

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val jackson = converters.find {
            it is MappingJackson2HttpMessageConverter
        } as MappingJackson2HttpMessageConverter

        val kotlinModule = KotlinModule.Builder()
            .configure(KotlinFeature.NullToEmptyCollection, true)
            .configure(KotlinFeature.NullToEmptyMap, true)
            .configure(KotlinFeature.NullIsSameAsDefault, true)
            .build()

        val mapper = jackson.objectMapper
            .registerModule(kotlinModule)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .configure(SerializationFeature.INDENT_OUTPUT, false)

        jackson.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON)

        converters.add(0, ProblemJsonConverter(mapper))
    }

    @Bean
    fun getMongo(): CoroutineClient {
        val mongoUrl = System.getenv(MONGO_ENV_VAR) ?: throw EnvVarNotFoundException(MONGO_ENV_VAR)
        log.info("Found environment variable $MONGO_ENV_VAR = $mongoUrl")

        return KMongo.createClient(mongoUrl).coroutine
    }

    @Bean
    fun getDatabase(coroutineClient: CoroutineClient): CoroutineDatabase {
        val mongoDbName = System.getenv(MONGO_DB_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_VAR = $mongoDbName")

        return coroutineClient.getDatabase(mongoDbName)
            .withReadConcern(ReadConcern.MAJORITY)
            .withWriteConcern(WriteConcern.MAJORITY)
    }

    @Bean
    fun getCollection(coroutineDatabase: CoroutineDatabase): CoroutineCollection<DBBusiness> {

        val collectionName = System.getenv(MONGO_DB_COLLECTION_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_VAR = $collectionName")

        return coroutineDatabase.getCollection(collectionName)
    }



    @Bean
    fun getWebClient(builder: WebClient.Builder): WebClientGetter {

        val placesEndpoint =
            System.getenv(PLACES_API_ENDPOINT_VAR) ?: throw EnvVarNotFoundException(PLACES_API_ENDPOINT_VAR)
        val placesKey = System.getenv(PLACES_API_KEY_VAR) ?: throw EnvVarNotFoundException(PLACES_API_KEY_VAR)

        val function: (Double, Double, Int, Int, Int) -> WebClient = {
            longitude, latitude, radius, limit, skip->

            val url = "$placesEndpoint?categories=commercial&filter=circle:$longitude,$latitude,${radius.toInt()}" +
                    "&bias=proximity:$longitude,$latitude&limit=$limit&skip=$skip&apiKey=$placesKey"
            WebClient.create(url)
        }
        return function
    }
}

fun main(args: Array<String>) {
    runApplication<BusinessApplication>(*args)
}