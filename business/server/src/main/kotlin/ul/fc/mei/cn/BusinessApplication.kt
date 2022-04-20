package ul.fc.mei.cn

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ul.fc.mei.cn.core.common.EnvVarNotFoundException
import ul.fc.mei.cn.core.common.ProblemJsonConverter
import ul.fc.mei.cn.core.model.BusinessFeature
import ul.fc.mei.cn.core.model.DBBusiness
import ul.fc.mei.cn.core.model.ModelFeatureArray
import ul.fc.mei.cn.core.model.ResourceGetter

@SpringBootApplication
class BusinessApplication

private const val MONGO_ENV_VAR = "MONGO_DATABASE_URL"
private const val MONGO_DB_VAR = "MONGO_DATABASE_NAME"
private const val MONGO_DB_COLLECTION_VAR = "MONGO_COLLECTION_NAME"
private const val PLACES_API_ENDPOINT_VAR = "PLACES_ENDPOINT"
private const val PLACES_API_KEY_VAR = "PLACES_API_KEY"


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
    fun getMongo(): MongoClient {
        val mongoUrl = System.getenv(MONGO_ENV_VAR) ?: throw EnvVarNotFoundException(MONGO_ENV_VAR)
        log.info("Found environment variable $MONGO_ENV_VAR = $mongoUrl")

        return KMongo.createClient(mongoUrl)
    }

    @Bean
    fun getDatabase(client: MongoClient): MongoDatabase {
        val mongoDbName = System.getenv(MONGO_DB_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_VAR = $mongoDbName")

        return client.getDatabase(mongoDbName)
            .withReadConcern(ReadConcern.MAJORITY)
            .withWriteConcern(WriteConcern.MAJORITY)
    }

    @Bean
    fun getCollection(database: MongoDatabase): MongoCollection<DBBusiness> {

        val collectionName = System.getenv(MONGO_DB_COLLECTION_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_COLLECTION_VAR = $collectionName")

        return database.getCollection<DBBusiness>(collectionName)
    }


    @Bean
    fun getWebClient(): ResourceGetter<ModelFeatureArray<BusinessFeature>> {
        val placesEndpoint =
            System.getenv(PLACES_API_ENDPOINT_VAR) ?: throw EnvVarNotFoundException(PLACES_API_ENDPOINT_VAR)
        val placesKey = System.getenv(PLACES_API_KEY_VAR) ?: throw EnvVarNotFoundException(PLACES_API_KEY_VAR)

        val function: ResourceGetter<ModelFeatureArray<BusinessFeature>> = { longitude, latitude, radius, limit, skip ->
            val restTemplate = RestTemplate()
            val url = "$placesEndpoint?categories=commercial&filter=circle:$longitude,$latitude,$radius" +
                    "&bias=proximity:$longitude,$latitude&limit=$limit&skip=$skip&apiKey=$placesKey"
            val responseEntity = restTemplate.exchange<ModelFeatureArray<BusinessFeature>>(
                url,
                HttpMethod.GET,
                null,
            )
            responseEntity
        }
        return function
    }
}

fun main(args: Array<String>) {
    runApplication<BusinessApplication>(*args)
}