package pt.fcul.keys

import java.util.*
import org.springframework.stereotype.Service
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput
import pt.fcul.keys.model.sha256
import pt.fcul.keys.repository.KeyRepository


@Service
class KeyService(
    val repo: KeyRepository
) {

    fun generateKey(input: KeyInput): KeyInfo {
        // TODO principal needs to have ADMIN scope

        val uuid = UUID.randomUUID().toString()
        val hash = uuid.sha256()

        val hashedInfo = KeyInfo(input.contact, input.quota, hash, 0, input.scope)
        val created = repo.createKey(hashedInfo)

        return KeyInfo(created.contact, created.quota, uuid, created.used, created.scope)
    }

    // TODO principal needs to have ADMIN scope, or inspecting itself
    fun inspectKey(key: String): KeyInfo = repo.readKey(key.sha256())

    fun editKeyInfo(info: KeyInfo): KeyInfo {
        // TODO principal needs to have ADMIN scope
        val hashed = KeyInfo(info.contact, info.quota, info.key.sha256(), info.used, info.scope)
        return repo.updateKey(hashed)
    }

    // TODO principal needs to have ADMIN scope, or revoking itself
    fun revokeKey(key: String) = repo.deleteKey(key.sha256())

    fun refreshKey(key: String): KeyInfo {
        // TODO needs transaction
        // TODO principal needs to have ADMIN scope, or refreshing itself

        val oldHash = key.sha256()
        val oldInfo = repo.readKey(oldHash)

        val uuid = UUID.randomUUID().toString()
        val newHash = uuid.sha256()

        val newInfo = KeyInfo(oldInfo.contact, oldInfo.quota, newHash, oldInfo.used, oldInfo.scope)
        val created = repo.createKey(newInfo)

        repo.deleteKey(oldHash)

        return KeyInfo(created.contact, created.quota, uuid, created.used, created.scope)
    }

    fun consumeKey(key: String, consume: KeyConsume) {
        // TODO raw key and KeyInfo comes from principal
        val hash = key.sha256()

        // TODO check if has valid scope

        repo.consumeKey(hash)
    }

}