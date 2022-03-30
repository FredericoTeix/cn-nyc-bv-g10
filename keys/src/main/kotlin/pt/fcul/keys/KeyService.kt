package pt.fcul.keys

import java.util.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import pt.fcul.keys.exceptions.ForbiddenAuthorization
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput
import pt.fcul.keys.model.KeyScope
import pt.fcul.keys.model.sha256
import pt.fcul.keys.repository.KeyRepository
import pt.fcul.keys.security.ApiAuthentication


@Service
class KeyService(
    val repo: KeyRepository
) {

    fun generateKey(input: KeyInput): KeyInfo {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        if (auth.keyInfo.scope != KeyScope.ADMIN) {
            throw ForbiddenAuthorization()
        }

        val uuid = UUID.randomUUID().toString()
        val hash = uuid.sha256()

        val hashedInfo = KeyInfo(input.contact, input.quota, hash, 0, input.scope)
        val created = repo.createKey(hashedInfo)

        return KeyInfo(created.contact, created.quota, uuid, created.used, created.scope)
    }

    fun inspectKey(): KeyInfo {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        val hashedKey = auth.principal.password

        return repo.readKey(hashedKey)
    }

    fun editKeyInfo(info: KeyInfo): KeyInfo {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        if (auth.keyInfo.scope != KeyScope.ADMIN) {
            throw ForbiddenAuthorization()
        }

        val hashed = KeyInfo(info.contact, info.quota, info.key.sha256(), info.used, info.scope)
        return repo.updateKey(hashed)
    }

    fun revokeKey() {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        val hashedKey = auth.principal.password

        repo.deleteKey(hashedKey)
    }

    fun refreshKey(): KeyInfo {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        val oldHash = auth.principal.password
        val oldInfo = auth.keyInfo

        val uuid = UUID.randomUUID().toString()
        val newHash = uuid.sha256()

        val newInfo = KeyInfo(oldInfo.contact, oldInfo.quota, newHash, oldInfo.used, oldInfo.scope)
        val created = repo.createKey(newInfo)
        // TODO needs transaction
        repo.deleteKey(oldHash)

        return KeyInfo(created.contact, created.quota, uuid, created.used, created.scope)
    }

    fun consumeKey(consume: KeyConsume) {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        val hashedKey = auth.principal.password

        // TODO check if has valid scope for the endpoint to consume

        repo.consumeKey(hashedKey)
    }

}