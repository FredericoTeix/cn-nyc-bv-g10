package pt.fcul.keys.model

import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.http.HttpMethod

enum class KeyScope {
    ADMIN, CONSUMER
}

data class KeyInput(
    @field:Email
    val contact: String,

    @field:Positive
    val quota: Int,

    val scope: KeyScope = KeyScope.CONSUMER
)

data class KeyInfo(
    @field:Email
    val contact: String,

    @field:Positive
    val quota: Int,

    @field:Pattern(
        regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\$",
        message = "Key must follow the UUID format"
    )
    val key: String,

    @field:PositiveOrZero
    val used: Int,

    val scope: KeyScope
)

data class KeyConsume(
    val path: String,
    val method: HttpMethod,
)

fun String.sha256(): String = DigestUtils.sha256Hex(this)