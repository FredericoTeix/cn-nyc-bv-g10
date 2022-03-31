package pt.fcul.keys.model

import org.springframework.http.HttpMethod

/*
---
endpoints:
  /trips:
    ADMIN:
     - "GET"
     - "PUT"
    CONSUMER:
     - "GET"
* */

data class ACLFile(
    val endpoints: Map<String, Map<KeyScope, Array<HttpMethod>>>
)

fun ACLFile.hasPermission(path: String, method: HttpMethod, scope: KeyScope) = endpoints
    .filterKeys { it.equals(path, true) } // TODO match path parameters with template
    .filterValues {
        it.any { (aclScope, aclMethods) ->
            aclScope == scope && aclMethods.contains(method)
        }
    }.any()