package pt.fcul.keys.exceptions

import javax.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException


@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(value = [HttpMediaTypeNotSupportedException::class])
    fun handleInvalidMediaType(
        req: HttpServletRequest,
        ex: HttpMediaTypeNotSupportedException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.UNSUPPORTED_MEDIA_TYPE.reasonPhrase,
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleInvalidArgument(
        req: HttpServletRequest,
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.BAD_REQUEST.reasonPhrase,
            status = HttpStatus.BAD_REQUEST,
            instance = req.requestURI,
            invalidParams = ex.fieldErrors.map {
                InvalidParam(
                    it.field,
                    it.defaultMessage
                )
            }
        )
    )

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class])
    fun handleMethodTypeMismatch(
        req: HttpServletRequest,
        ex: MethodArgumentTypeMismatchException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.BAD_REQUEST.reasonPhrase,
            detail = "Invalid value [${ex.value}] for parameter ${ex.name}",
            status = HttpStatus.BAD_REQUEST,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    fun handleMethodNotSupported(
        req: HttpServletRequest,
        ex: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.METHOD_NOT_ALLOWED.reasonPhrase,
            status = HttpStatus.METHOD_NOT_ALLOWED,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun handleMessageNotReadableException(
        req: HttpServletRequest,
        ex: HttpMessageNotReadableException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.BAD_REQUEST.reasonPhrase,
            status = HttpStatus.BAD_REQUEST,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [HttpException::class])
    fun handleHttpException(
        req: HttpServletRequest,
        ex: HttpException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = ex.status.reasonPhrase,
            status = ex.status,
            detail = ex.localizedMessage,
            instance = req.requestURI,
            invalidParams = ex.invalidParams
        ),
        ex.httpHeaders
    )

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun handleNotFound(
        req: HttpServletRequest,
        ex: NoHandlerFoundException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.NOT_FOUND.reasonPhrase,
            status = HttpStatus.NOT_FOUND,
            detail = ex.localizedMessage,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun handleForbidden(
        req: HttpServletRequest,
        ex: AccessDeniedException
    ): ResponseEntity<ProblemJson> = handleException(
        ProblemJson(
            title = HttpStatus.FORBIDDEN.reasonPhrase,
            status = HttpStatus.FORBIDDEN,
            instance = req.requestURI
        )
    )

    @ExceptionHandler(value = [Exception::class])
    fun handleInternalServerError(
        req: HttpServletRequest,
        ex: Exception
    ): ResponseEntity<ProblemJson>? {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        logger.error(status.reasonPhrase, ex)

        return handleException(
            ProblemJson(
                title = status.reasonPhrase,
                status = status,
                instance = req.requestURI
            )
        )
    }

    private fun handleException(problem: ProblemJson, headers: HttpHeaders = HttpHeaders()) =
        ResponseEntity.status(problem.status)
            .headers(headers)
            .body(problem)
}