package pt.fcul.keys.model

import org.springframework.http.HttpMethod

/*
---
endpoints:
  /trips:
    GET:
     - "ADMIN"
     - "CONSUMER"
    PUT:
     - "ADMIN"
* */

data class ACLFile(
    val endpoints: Map<String, Map<HttpMethod, Array<KeyScope>>>
)

fun ACLFile.hasPermission(path: String, method: HttpMethod, scope: KeyScope) = endpoints
    .filterKeys { it.equals(path, true) } // TODO match path parameters with template
    .filterValues {
        it.any { (aclMethod, aclScopes) ->
            aclMethod == method && aclScopes.contains(scope)
        }
    }.any()