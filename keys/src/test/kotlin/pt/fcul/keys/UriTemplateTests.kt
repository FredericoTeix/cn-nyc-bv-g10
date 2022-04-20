package pt.fcul.keys

import org.junit.jupiter.api.Test
import org.springframework.util.Assert
import org.springframework.web.util.UriTemplate
import pt.fcul.keys.model.parsePath

class UriTemplateTests {

    @Test
    fun `Exact path and template should match`() {
        val template = UriTemplate("/trips")
        val path = "/trips".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(matches, "Exact paths should match")
    }

    @Test
    fun `Path and template with parameter should match`() {
        val template = UriTemplate("/trips/{tid}")
        val path = "/trips/exampleId".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(matches, "A template should match independently of its parameter values")
    }

    @Test
    fun `Path without parameter shouldn't match template with parameter`() {
        val template = UriTemplate("/trips/{tid}")
        val path = "/trips".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(!matches, "A template can't match a path without all parameters")
    }

    @Test
    fun `Path with parameter shouldn't match template without parameter`() {
        val template = UriTemplate("/trips")
        val path = "/trips/exampleId".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(!matches, "A path can't have more fields than it's template")
    }

    @Test
    fun `Long path should match`() {
        val template = UriTemplate("/trips/path/{tid}/resource/bad/organization/{oid}/{vid}")
        val path = "/trips/path/exampleId/resource/bad/organization/1923/v0.3.2".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(matches, "Path should match template")
    }

    @Test
    fun `Full URI should match a template with path only`() {
        val template = UriTemplate("/trips/{tid}/resource")
        val path = "https://www.google.com:8080/trips/exampleId/resource?query=123#bottom".parsePath()

        val matches = template.matches(path)

        Assert.isTrue(matches, "Path should match template")
    }

}