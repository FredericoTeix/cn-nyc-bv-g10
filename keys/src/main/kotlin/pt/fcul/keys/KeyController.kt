package pt.fcul.keys

import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.fcul.keys.common.Links
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput

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
        @RequestBody consume: KeyConsume
    ): ResponseEntity<Unit> {
        service.consumeKey(consume)
        return ResponseEntity.ok().build()
    }

}