package pt.fcul.keys

import java.util.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import pt.fcul.keys.exceptions.ForbiddenAuthorization
import pt.fcul.keys.exceptions.KeyQuotaExceededException
import pt.fcul.keys.model.ACLFile
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput
import pt.fcul.keys.model.KeyScope
import pt.fcul.keys.model.hasPermission
import pt.fcul.keys.model.sha256
import pt.fcul.keys.repository.KeyRepository
import pt.fcul.keys.security.ApiAuthentication


@Service
class KeyService(
    val repo: KeyRepository,
    val acl: ACLFile
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

    fun editKeyInfo(info: KeyInfo) {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        if (auth.keyInfo.scope != KeyScope.ADMIN) {
            throw ForbiddenAuthorization()
        }

        val hashedKey = info.key.sha256()
        val newInfo = KeyInfo(info.contact, info.quota, hashedKey, info.used, info.scope)
        repo.updateKey(hashedKey, newInfo)
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

        repo.updateKey(oldHash, newInfo)
        return newInfo
    }

    fun consumeKey(consume: KeyConsume) {
        val auth = SecurityContextHolder.getContext().authentication as ApiAuthentication
        val hashedKey = auth.principal.password
        val currInfo = auth.keyInfo

        if (!acl.hasPermission(consume.path, consume.method, currInfo.scope)) {
            throw ForbiddenAuthorization()
        }

        if (currInfo.used >= currInfo.quota) {
            throw KeyQuotaExceededException(hashedKey, currInfo.quota)
        }

        val newInfo = KeyInfo(currInfo.contact, currInfo.quota, hashedKey, currInfo.used + 1, currInfo.scope)
        repo.updateKey(hashedKey, newInfo)
    }

}