package pt.fcul.keys.model

import java.net.URI
import org.springframework.http.HttpMethod
import org.springframework.web.util.UriTemplate

data class ACLFile(
    val endpoints: Map<String, Map<KeyScope, Map<HttpMethod, Int>>>
)

fun ACLFile.toACL(): ACL {
    val resources = endpoints.map { (path, subjects) ->
        val template = UriTemplate(path)
        val aclSubjects = subjects.map { (scope, methods) ->
            ACLSubject(scope, methods)
        }

        ACLPathResource(template, aclSubjects)
    }

    return ACL(resources)
}

fun String.parsePath(): String {
    val uri = URI(this)
    val path = uri.rawPath

    return if (path.length > 1) path.removeSuffix("/")
    else path
}

data class ACL(
    val endpoints: List<ACLPathResource>
)

data class ACLPathResource(
    val template: UriTemplate,
    val subjects: List<ACLSubject>
)

data class ACLSubject(
    val scope: KeyScope,
    val methods: Map<HttpMethod, Int>
)

fun ACL.consume(path: String, method: HttpMethod, scope: KeyScope): Int? = endpoints
    .filter { resource -> resource.template.matches(path.parsePath()) }
    .flatMap { it.subjects }
    .firstOrNull { it.scope == scope }
    ?.methods?.get(method)