package pt.fcul.keys.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

open class HttpException(
    message: String,
    val status: HttpStatus,
    val invalidParams: List<InvalidParam>? = null
) : RuntimeException(message) {
    val httpHeaders = HttpHeaders()
}

open class UnauthorizedException(
    message: String = HttpStatus.UNAUTHORIZED.reasonPhrase
) : HttpException(HttpStatus.UNAUTHORIZED.reasonPhrase, HttpStatus.UNAUTHORIZED)

open class ForbiddenAuthorization(
    message: String = HttpStatus.FORBIDDEN.reasonPhrase
) : HttpException(message, HttpStatus.FORBIDDEN)

open class BadRequestException(
    message: String,
    invalidParams: List<InvalidParam>? = null
) : HttpException(message, HttpStatus.BAD_REQUEST, invalidParams)

open class NotFoundException(message: String) : HttpException(message, HttpStatus.NOT_FOUND)