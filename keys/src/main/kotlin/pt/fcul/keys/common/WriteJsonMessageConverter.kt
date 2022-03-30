package pt.fcul.keys.common

import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Type
import kotlin.reflect.KClass
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractGenericHttpMessageConverter
import pt.fcul.keys.exceptions.ProblemJson

open class WriteJsonMessageConverter<T : Any>(
    private val mapper: ObjectMapper,
    private val clazz: KClass<T>,
    private val mediaType: MediaType
) : AbstractGenericHttpMessageConverter<T>(mediaType) {

    override fun supports(clazz: Class<*>) = clazz == this.clazz.java

    override fun read(type: Type, contextClass: Class<*>?, inputMessage: HttpInputMessage) =
        throw UnsupportedOperationException("This converter is write only")

    override fun readInternal(clazz: Class<out T>, inputMessage: HttpInputMessage) =
        throw UnsupportedOperationException("This converter is write only")

    override fun writeInternal(t: T, type: Type?, outputMessage: HttpOutputMessage) {
        outputMessage.headers.set(HttpHeaders.CONTENT_TYPE, mediaType.toString())

        // write the body as bytes instead of delegating the write to the json converter
        // since any exception of the parsing would result in a malformed output
        val bytes = mapper.writeValueAsBytes(t)
        outputMessage.body.use {
            it.write(bytes)
        }
    }
}

class ProblemJsonConverter(
    mapper: ObjectMapper
) : WriteJsonMessageConverter<ProblemJson>(mapper, ProblemJson::class, MediaType.APPLICATION_PROBLEM_JSON)