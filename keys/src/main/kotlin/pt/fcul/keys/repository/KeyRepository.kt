package pt.fcul.keys.repository

import pt.fcul.keys.model.KeyInfo

interface KeyRepository {

    fun createKey(info: KeyInfo): KeyInfo

    fun readKey(key: String): KeyInfo

    fun updateKey(key: String, info: KeyInfo)

    fun deleteKey(key: String)

}