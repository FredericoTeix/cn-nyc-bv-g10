package pt.fcul.keys.utils

data class DuplicateKeyException(
    val key: String
): Exception("An API Key with hash $key already exists")

data class KeyDoesntExistException(
    val key: String
): Exception("The API Key with hash $key doesn't exist")

data class KeyQuotaExceededException(
    val key: String,
    val quota: Int
): Exception("The API Key with hash $key exceeded its quota of $quota")