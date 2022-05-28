package pt.fcul.keys

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import java.io.File
import org.litote.kmongo.KMongo
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.fcul.keys.common.ProblemJsonConverter
import pt.fcul.keys.exceptions.EnvVarNotFoundException
import pt.fcul.keys.exceptions.InvalidAclFileException
import pt.fcul.keys.model.ACL
import pt.fcul.keys.model.ACLFile
import pt.fcul.keys.model.toACL
import pt.fcul.keys.security.AuthFilter


private const val MONGO_ENV_VAR = "MONGO_DATABASE_URL"
private const val MONGO_DB_VAR = "MONGO_DATABASE_NAME"
private const val ACL_FILE_VAR = "ACL_FILE"

@SpringBootApplication
class KeysApplication

@Configuration
@EnableWebMvc
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
    fun getDb(client: MongoClient): MongoDatabase {
        val mongoDbName = System.getenv(MONGO_DB_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_VAR = $mongoDbName")

        return client.getDatabase(mongoDbName)
            .withReadConcern(ReadConcern.MAJORITY)
            .withWriteConcern(WriteConcern.MAJORITY)
    }

    @Bean
    fun getACL(): ACL {
        val filePath = System.getenv(ACL_FILE_VAR) ?: throw EnvVarNotFoundException(ACL_FILE_VAR)
        log.info("Found environment variable $ACL_FILE_VAR = $filePath")

        val file = File(filePath)
        val mapper = ObjectMapper(YAMLFactory()).apply {
            registerKotlinModule()
        }

        if (!file.exists() || !file.canRead()) {
            throw InvalidAclFileException(filePath)
        }

        if (!file.extension.equals("yml", true) && !file.extension.equals("yaml", true)) {
            throw InvalidAclFileException(filePath)
        }

        return mapper.readValue(file, ACLFile::class.java).toACL()
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    val filter: AuthFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterAfter(
                filter,
                UsernamePasswordAuthenticationFilter::class.java
            )
    }
}

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration()

fun main(args: Array<String>) {
    runApplication<KeysApplication>(*args)
}
