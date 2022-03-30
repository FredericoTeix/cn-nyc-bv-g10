package pt.fcul.keys.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.util.StdConverter
import org.springframework.http.HttpStatus

data class ProblemJson(
    val type: String? = null,
    val title: String,
    @JsonSerialize(converter = HttpStatusJacksonConverter::class)
    val status: HttpStatus,
    val detail: String? = null,
    val instance: String,
    @JsonProperty("invalid-params")
    val invalidParams: List<InvalidParam>? = null
)

data class InvalidParam(
    val name: String,
    val reason: String? = null
)

class HttpStatusJacksonConverter : StdConverter<HttpStatus, Int>() {
    override fun convert(value: HttpStatus?) = value?.value()
}