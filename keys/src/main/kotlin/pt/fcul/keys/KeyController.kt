package pt.fcul.keys

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput
import pt.fcul.keys.utils.Links

const val API_KEY_HEADER = "x-api-key"

@RestController
class KeyController(
    val service: KeyService
) {

    @PostMapping(Links.Key)
    suspend fun generateKey(
        @RequestBody input: KeyInput
    ): ResponseEntity<KeyInfo> {
        val info = service.generateKey(input)
        return ResponseEntity.ok(info)
    }

    @DeleteMapping(Links.Key)
    suspend fun revokeKey(
        // TODO should be obtained from principal or argument resolver, after authenticated
        @RequestHeader(API_KEY_HEADER) key: String
    ): ResponseEntity<Unit> {
        service.revokeKey(key)
        return ResponseEntity.ok().build()
    }

    @GetMapping(Links.Key)
    suspend fun inspectKey(
        // TODO should be obtained from principal or argument resolver, after authenticated
        @RequestHeader(API_KEY_HEADER) key: String
    ): ResponseEntity<KeyInfo> {
        val info = service.inspectKey(key)
        return ResponseEntity.ok(info)
    }

    @PutMapping(Links.Key)
    suspend fun editKeyInfo(
        @RequestBody info: KeyInfo
    ): ResponseEntity<Unit> {
        service.editKeyInfo(info)
        return ResponseEntity.ok().build()
    }

    @PutMapping(Links.RefreshKey)
    suspend fun refreshKey(
        // TODO should be obtained from principal or argument resolver, after authenticated
        @RequestHeader(API_KEY_HEADER) key: String
    ): ResponseEntity<KeyInfo> {
        val info = service.refreshKey(key)
        return ResponseEntity.ok(info)
    }

    @PutMapping(Links.ConsumeKey)
    suspend fun consumeKey(
        // TODO should be obtained from principal or argument resolver, after authenticated
        @RequestHeader(API_KEY_HEADER) key: String,
        @RequestBody consume: KeyConsume
    ): ResponseEntity<Unit> {
        service.consumeKey(key, consume)
        return ResponseEntity.ok().build()
    }

}