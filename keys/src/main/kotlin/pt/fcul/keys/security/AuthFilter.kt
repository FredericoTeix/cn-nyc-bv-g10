package pt.fcul.keys.security

import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.apache.commons.io.output.WriterOutputStream
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import pt.fcul.keys.exceptions.HttpException
import pt.fcul.keys.exceptions.ProblemJson
import pt.fcul.keys.exceptions.UnauthorizedException
import pt.fcul.keys.model.sha256
import pt.fcul.keys.repository.KeyRepository

const val API_KEY_HEADER = "x-api-key"

@Component
class AuthFilter(
    val keyRepository: KeyRepository,
    val mapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            if (request.requestURI.startsWith("/actuator")) {
                chain.doFilter(request, response)
                return
            }

            val apiKey = request.getHeader(API_KEY_HEADER) ?: throw UnauthorizedException()
            val keyInfo = keyRepository
                .runCatching {
                    return@runCatching readKey(apiKey.sha256())
                }
                .getOrDefault(null) ?: throw UnauthorizedException()

            val authentication = ApiAuthentication(apiKey, keyInfo)

            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)
        } catch (e: HttpException) {
            return handleException(request, response, e.status, e.httpHeaders)
        } catch (e: Exception) {
            return handleException(request, response, HttpStatus.FORBIDDEN)
        }
    }


    private fun handleException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        status: HttpStatus,
        headers: HttpHeaders? = null
    ) {
        response.status = status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        headers?.forEach { key, values -> values.forEach { value -> response.addHeader(key, value) } }

        response.writer.use {
            WriterOutputStream(it).use { stream ->
                stream.write(
                    mapper.writeValueAsBytes(
                        ProblemJson(
                            title = status.reasonPhrase,
                            status = status,
                            instance = request.requestURI
                        )
                    )
                )
            }
        }
    }
}