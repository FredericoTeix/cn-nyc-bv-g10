package pt.fcul.keys.exceptions

data class DuplicateKeyException(
    val key: String
) : Exception("An API Key with hash $key already exists")

data class KeyDoesntExistException(
    val key: String
) : NotFoundException("The API Key with hash $key doesn't exist")

data class KeyQuotaExceededException(
    val key: String,
    val quota: Int
) : ForbiddenAuthorization("The API Key with hash $key exceeded its quota of $quota")

class EnvVarNotFoundException(
    envVariable: String
) : RuntimeException("Environment variable $envVariable not defined!")

class InvalidAclFileException(
    path: String
) : RuntimeException("Invalid ACL File in $path")