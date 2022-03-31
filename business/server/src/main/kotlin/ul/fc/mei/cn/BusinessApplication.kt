package ul.fc.mei.cn

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import org.litote.kmongo.coroutine.CoroutineClient
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ul.fc.mei.cn.business.common.ProblemJsonConverter
import ul.fc.mei.cn.business.utils.EnvVarNotFoundException

@SpringBootApplication
class BusinessApplication

private const val MONGO_ENV_VAR = "MONGO_DATABASE_URL"
private const val MONGO_DB_VAR = "MONGO_DATABASE_NAME"


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
}

fun main(args: Array<String>) {
    runApplication<BusinessApplication>(*args)
}