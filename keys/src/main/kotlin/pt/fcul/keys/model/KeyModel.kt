package pt.fcul.keys.model

import org.apache.commons.codec.digest.DigestUtils
import org.springframework.http.HttpMethod

enum class KeyScope {
    ADMIN, CONSUMER
}

data class KeyInput(
    val contact: String,
    val quota: Int,
    val scope: KeyScope = KeyScope.CONSUMER
)

data class Key(
    val key: String,
)

data class KeyInfo(
    val contact: String,
    val quota: Int,
    val key: String,
    val used: Int,
    val scope: KeyScope
)

data class KeyConsume(
    val path: String,
    val method: HttpMethod,
)

fun String.sha256(): String = DigestUtils.sha256Hex(this)