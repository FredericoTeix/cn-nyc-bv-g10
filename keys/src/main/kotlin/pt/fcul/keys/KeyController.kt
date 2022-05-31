package pt.fcul.keys

import javax.validation.Valid
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import pt.fcul.keys.common.Links
import pt.fcul.keys.exceptions.BadConsumeRequestException
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput

const val ORIGINAL_PATH_HEADER = "X-Auth-Request-Redirect"
const val ORIGINAL_METHOD_HEADER = "X-Original-Method"

@RestController
class KeyController(
    val service: KeyService
) {

    @PostMapping(Links.Key)
    fun generateKey(
        @Valid @RequestBody input: KeyInput
    ): ResponseEntity<KeyInfo> {
        val info = service.generateKey(input)
        return ResponseEntity.ok(info)
    }

    @DeleteMapping(Links.Key)
    fun revokeKey(): ResponseEntity<Unit> {
        service.revokeKey()
        return ResponseEntity.ok().build()
    }

    @GetMapping(Links.Key)
    fun inspectKey(): ResponseEntity<KeyInfo> {
        val info = service.inspectKey()
        return ResponseEntity.ok(info)
    }

    @PutMapping(Links.Key)
    fun editKeyInfo(
        @Valid @RequestBody info: KeyInfo
    ): ResponseEntity<Unit> {
        service.editKeyInfo(info)
        return ResponseEntity.ok().build()
    }

    @PutMapping(Links.RefreshKey)
    fun refreshKey(): ResponseEntity<KeyInfo> {
        val info = service.refreshKey()
        return ResponseEntity.ok(info)
    }

    @PutMapping(Links.ConsumeKey)
    fun consumeKey(
        @RequestBody consume: KeyConsume?,
        @RequestHeader(ORIGINAL_PATH_HEADER) originalPath: String?,
        @RequestHeader(ORIGINAL_METHOD_HEADER) originalMethod: String?,
    ): ResponseEntity<Unit> {
        val keyConsume = consume ?: runCatching {
            KeyConsume(originalPath!!, HttpMethod.resolve(originalMethod)!!)
        }.getOrElse {
            throw BadConsumeRequestException()
        }

        service.consumeKey(keyConsume)
        return ResponseEntity.ok().build()
    }

}