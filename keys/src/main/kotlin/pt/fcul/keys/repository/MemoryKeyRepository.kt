package pt.fcul.keys.repository

import org.springframework.stereotype.Repository
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyScope
import pt.fcul.keys.exceptions.DuplicateKeyException
import pt.fcul.keys.exceptions.KeyDoesntExistException
import pt.fcul.keys.exceptions.KeyQuotaExceededException

val ADMIN_KEY = KeyInfo(
    "user@mail.com",
    1000,
    "5d962bcc577343eff09091f902ca52d125ecaab8f3fe3cd2e92227dd916be1d3",
    0,
    KeyScope.ADMIN
)

@Repository
class MemoryKeyRepository : KeyRepository {

    val data: MutableMap<String, KeyInfo> = mutableMapOf()

    override fun createKey(info: KeyInfo): KeyInfo {
        if (data.containsKey(info.key)) {
            throw DuplicateKeyException(info.key)
        }

        data[info.key] = info
        return info
    }

    override fun readKey(key: String) = data[key] ?: throw KeyDoesntExistException(key)

    override fun updateKey(info: KeyInfo) = data.computeIfPresent(info.key) { _, _ ->
        info
    } ?: throw KeyDoesntExistException(info.key)

    override fun deleteKey(key: String) {
        data.remove(key) ?: throw KeyDoesntExistException(key)
    }

    override fun consumeKey(key: String) {
        data.computeIfPresent(key) { _, info ->
            if (info.used >= info.quota) {
                throw KeyQuotaExceededException(key, info.quota)
            }

            KeyInfo(info.contact, info.quota, info.key, info.used + 1, info.scope)
        } ?: throw KeyDoesntExistException(key)
    }
}