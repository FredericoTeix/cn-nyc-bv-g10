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
    "de54bbaae23bb7c83801437e757ae980dbe7b3b61fc250d26923bf1511115dc7",
    0,
    KeyScope.ADMIN
)

@Repository
class MemoryKeyRepository : KeyRepository {

    val data: MutableMap<String, KeyInfo> = mutableMapOf(ADMIN_KEY.key to ADMIN_KEY)

    override fun createKey(info: KeyInfo): KeyInfo {
        if (data.containsKey(info.key)) {
            throw DuplicateKeyException(info.key)
        }

        data[info.key] = info
        return info
    }

    override fun readKey(key: String) = data[key] ?: throw KeyDoesntExistException(key)

    override fun updateKey(key: String, info: KeyInfo) {
        data.computeIfPresent(info.key) { _, _ ->
            info
        } ?: throw KeyDoesntExistException(info.key)
    }

    override fun deleteKey(key: String) {
        data.remove(key) ?: throw KeyDoesntExistException(key)
    }
}