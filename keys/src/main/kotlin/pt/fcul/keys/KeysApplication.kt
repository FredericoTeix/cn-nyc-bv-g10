package pt.fcul.keys

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.fcul.keys.common.ProblemJsonConverter

@SpringBootApplication
class KeysApplication

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
}

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
//	val keyService: KeyService
) : WebSecurityConfigurerAdapter() {

	override fun configure(auth: AuthenticationManagerBuilder) {
//		auth.userDetailsService(keyService)
	}

	override fun configure(http: HttpSecurity) {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.addFilterAfter(
//				JwtFilter(),
//				UsernamePasswordAuthenticationFilter::class.java
//			)
	}
}

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration()

fun main(args: Array<String>) {
    runApplication<KeysApplication>(*args)
}
