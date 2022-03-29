package pt.fcul.keys.common

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

open class HttpException(
    message: String,
    val status: HttpStatus,
    val invalidParams: List<InvalidParam>? = null
) : RuntimeException(message) {
    val httpHeaders = HttpHeaders()
}

class UnauthorizedException : HttpException(HttpStatus.UNAUTHORIZED.reasonPhrase, HttpStatus.UNAUTHORIZED)
class ForbiddenAuthorization : HttpException(HttpStatus.FORBIDDEN.reasonPhrase, HttpStatus.FORBIDDEN)

open class BadRequestException(
    message: String, invalidParams: List<InvalidParam>? = null
) : HttpException(message, HttpStatus.BAD_REQUEST, invalidParams)

open class NotFoundException(message: String) : HttpException(message, HttpStatus.NOT_FOUND)

open class UnprocessableEntityException(message: String) : HttpException(message, HttpStatus.UNPROCESSABLE_ENTITY)

data class DuplicateKeyException(
    val key: String
): Exception("An API Key with hash $key already exists")

data class KeyDoesntExistException(
    val key: String
): NotFoundException("The API Key with hash $key doesn't exist")

data class KeyQuotaExceededException(
    val key: String,
    val quota: Int
): UnprocessableEntityException("The API Key with hash $key exceeded its quota of $quota")