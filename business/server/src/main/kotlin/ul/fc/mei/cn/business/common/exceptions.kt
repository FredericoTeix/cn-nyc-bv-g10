package ul.fc.mei.cn.business.utils

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import ul.fc.mei.cn.business.common.ProblemJson
import javax.servlet.http.HttpServletRequest
import kotlin.reflect.full.findAnnotation

open class HttpException(
    message: String,
    val status: HttpStatus
) : RuntimeException(message) {
    val httpHeaders = HttpHeaders()
}


class EnvVarNotFoundException(envVariable: String) : RuntimeException("Environment variable $envVariable not defined!")

open class BadRequestException(message: String) : HttpException(message, HttpStatus.BAD_REQUEST)
open class NotFoundException(message: String) : HttpException(message, HttpStatus.NOT_FOUND)

@RestControllerAdvice
class BusinessExceptionHandler {

    private val logger = LoggerFactory.getLogger(BusinessExceptionHandler::class.java)

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleMessageNotReadable(
        req: HttpServletRequest,
        ex: HttpMessageNotReadableException
    ): ResponseEntity<ProblemJson> {
        val status = HttpStatus.BAD_REQUEST
        if (ex.cause is MissingKotlinParameterException) {
            val cause = ex.cause as MissingKotlinParameterException
            val parameter = cause.parameter
            var parameterName = parameter.name

            val jsonAnnotation = parameter.findAnnotation<JsonProperty>()
            if (jsonAnnotation != null)
                parameterName = jsonAnnotation.value

            return handleException(
                ProblemJson(
                    title = "Missing required parameter",
                    status = status,
                    detail = "Missing body parameter: $parameterName",
                    instance = req.requestURI
                )
            )
        }

        return handleException(
            ProblemJson(
                title = status.reasonPhrase,
                status = status,
                detail = ex.localizedMessage,
                instance = req.requestURI
            )
        )
    }

    @ExceptionHandler(value = [HttpException::class])
    fun handleHttpException(
        req: HttpServletRequest,
        ex: HttpException
    ): ResponseEntity<ProblemJson> {
        println("In Exception Handler")
        return handleException(
            ProblemJson(
                title = ex.status.reasonPhrase,
                status = ex.status,
                detail = ex.localizedMessage,
                instance = req.requestURI
            ),
            ex.httpHeaders
        )
    }

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun handleNotFound(
        req: HttpServletRequest,
        ex: NoHandlerFoundException
    ): ResponseEntity<ProblemJson> {
        val status = HttpStatus.NOT_FOUND
        return handleException(
            ProblemJson(
                title = status.reasonPhrase,
                status = status,
                detail = ex.localizedMessage,
                instance = req.requestURI
            )
        )
    }

    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    fun handleMethodNotSupported(
        req: HttpServletRequest,
        ex: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ProblemJson> {
        val status = HttpStatus.METHOD_NOT_ALLOWED
        return handleException(
            ProblemJson(
                title = status.reasonPhrase,
                status = status,
                detail = ex.localizedMessage,
                instance = req.requestURI
            )
        )
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleInternalServerError(
        req: HttpServletRequest,
        ex: Exception
    ): ResponseEntity<ProblemJson> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        logger.error(status.reasonPhrase, ex)

        return handleException(
            ProblemJson(
                title = status.reasonPhrase,
                status = status,
                detail = ex.localizedMessage,
                instance = req.requestURI
            )
        )
    }
}

private fun handleException(problem: ProblemJson, headers: HttpHeaders = HttpHeaders()) =
    ResponseEntity.status(problem.status)
        .headers(headers)
        .body(problem)