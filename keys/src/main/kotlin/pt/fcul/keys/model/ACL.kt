package pt.fcul.keys.model

import java.net.URI
import org.springframework.http.HttpMethod
import org.springframework.web.util.UriTemplate

data class ACLFile(
    val endpoints: ACLFileEndpoints
)

data class ACLFileEndpoints(
    val unauthenticated: Map<String, List<HttpMethod>>?,
    val authorized: Map<String, Map<KeyScope, Map<HttpMethod, Int>>>?
)

fun ACLFile.toACL(): ACL {
    val unauthenticated = endpoints.unauthenticated?.map { (path, methods) ->
        val template = UriTemplate(path)

        ACLUnauthenticated(template, methods)
    } ?: emptyList()

    val authorized = endpoints.authorized?.map { (path, subjects) ->
        val template = UriTemplate(path)
        val aclSubjects = subjects.map { (scope, methods) ->
            ACLSubject(scope, methods)
        }

        ACLAuthorized(template, aclSubjects)
    } ?: emptyList()

    return ACL(unauthenticated, authorized)
}

fun String.parsePath(): String {
    val uri = URI(this)
    val path = uri.rawPath

    return if (path.length > 1) path.removeSuffix("/")
    else path
}

data class ACL(
    val unauthenticated: List<ACLUnauthenticated>,
    val authorized: List<ACLAuthorized>
)

data class ACLUnauthenticated(
    val template: UriTemplate,
    val methods: List<HttpMethod>
)

data class ACLAuthorized(
    val template: UriTemplate,
    val subjects: List<ACLSubject>
)

data class ACLSubject(
    val scope: KeyScope,
    val methods: Map<HttpMethod, Int>
)

fun ACL.isUnauthenticated(path: String, method: HttpMethod): Boolean = unauthenticated
    .firstOrNull { resource -> resource.template.matches(path.parsePath()) }
    ?.methods?.contains(method) ?: false

fun ACL.consume(path: String, method: HttpMethod, scope: KeyScope): Int? = authorized
    .filter { resource -> resource.template.matches(path.parsePath()) }
    .flatMap { it.subjects }
    .firstOrNull { it.scope == scope }
    ?.methods?.get(method)