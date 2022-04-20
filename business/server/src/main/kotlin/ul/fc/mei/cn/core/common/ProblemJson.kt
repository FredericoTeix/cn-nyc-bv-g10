package ul.fc.mei.cn.core.common


import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.util.StdConverter
import org.springframework.http.HttpStatus

data class ProblemJson(
    val type: String? = null,
    val title: String,
    @JsonSerialize(converter = HttpStatusJacksonConverter::class)
    val status: HttpStatus,
    val detail: String,
    val instance: String
)

class HttpStatusJacksonConverter : StdConverter<HttpStatus, Int>() {
    override fun convert(value: HttpStatus?) = value?.value()
}