package pt.fcul.keys.model

import java.net.URI
import org.springframework.http.HttpMethod
import org.springframework.web.util.UriTemplate

data class ACLFile(
    val endpoints: Map<String, Map<KeyScope, Array<HttpMethod>>>
)

fun ACLFile.toACL(): ACL {
    val resources = endpoints.map { (path, subjects) ->
        val template = UriTemplate(path)
        val aclSubjects = subjects.map { (scope, methods) ->
            ACLSubject(scope, methods.toList())
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
    val methods: List<HttpMethod>
)

fun ACL.hasPermission(path: String, method: HttpMethod, scope: KeyScope) = endpoints
    .filter { resource -> resource.template.matches(path.parsePath()) }
    .filter { resource ->
        resource.subjects.any { subject ->
            subject.scope == scope && subject.methods.contains(method)
        }
    }.any()