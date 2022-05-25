package pt.fcul.keys

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import pt.fcul.keys.model.KeyConsume
import pt.fcul.keys.model.KeyInfo
import pt.fcul.keys.model.KeyInput
import pt.fcul.keys.model.KeyScope
import pt.fcul.keys.security.API_KEY_HEADER

const val ADMIN_KEY = "123e4567-e89b-42d3-a456-556642440000"
const val USER_KEY = "123ee89b-4567-a456-42d3-440000556642"

@SpringBootTest
@AutoConfigureMockMvc
class KeysApplicationTests {

    protected val objectMapper: ObjectMapper = jacksonObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)!!

    @Autowired
    lateinit var mock: MockMvc

    @Test
    fun contextLoads() {
    }

    @Test
    fun `Requests should be authenticated`() {
        mock.get("/key")
            .andExpect {
                status { isUnauthorized() }
            }

        mock.get("/key/consume")
            .andExpect {
                status { isUnauthorized() }
            }

        mock.get("/key/refresh")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun `Admin key exists`() {
        mock.get("/key") {
            header(API_KEY_HEADER, ADMIN_KEY)
        }.andExpect {
            status {
                isOk()
            }
            content {
                contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                jsonPath("$.scope") {
                    value(KeyScope.ADMIN.name)
                }
            }
        }
    }

    @Test
    fun `Nonexistent key is unauthorized`() {
        mock.get("/key") {
            header(API_KEY_HEADER, "nonexistent")
        }.andExpect {
            status {
                isUnauthorized()
            }
        }
    }

    @Test
    fun `Key must have valid contact`() {
        mock.post("/key") {
            header(API_KEY_HEADER, ADMIN_KEY)
            contentType = MediaType.APPLICATION_JSON
            content = KeyInput("invalid mail", 10, KeyScope.CONSUMER)
        }.andExpect {
            status {
                isBadRequest()
            }
        }
    }

    @Test
    fun `Key must have positive quota`() {
        mock.post("/key") {
            header(API_KEY_HEADER, ADMIN_KEY)
            contentType = MediaType.APPLICATION_JSON
            content = KeyInput("ephemeral@mail.com", -1, KeyScope.CONSUMER)
        }.andExpect {
            status {
                isBadRequest()
            }
        }
    }

    @Test
    fun `Normal user can't create keys`() {
        mock.post("/key") {
            header(API_KEY_HEADER, USER_KEY)

            val key = KeyInput("ephemeral@mail.com", 10, KeyScope.CONSUMER)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(key)
        }.andExpect {
            status {
                isForbidden()
            }
        }
    }

    @Test
    fun `Admin can create and delete keys`() {
        val result = mock.post("/key") {
            header(API_KEY_HEADER, ADMIN_KEY)

            val key = KeyInput("ephemeral@mail.com", 10, KeyScope.CONSUMER)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(key)
        }.andExpect {
            status {
                isOk()
            }
            content {
                contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                jsonPath("$.scope") {
                    value(KeyScope.CONSUMER.name)
                }
            }
        }.andReturn()

        val key = objectMapper.readValue(result.response.contentAsString, KeyInfo::class.java)

        mock.delete("/key") {
            header(API_KEY_HEADER, key.key)
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun `Consume key from nginx redirect`() {
        mock.put("/key/consume") {
            header(API_KEY_HEADER, ADMIN_KEY)
            header(ORIGINAL_PATH_HEADER, "/trips")
            header(ORIGINAL_METHOD_HEADER, "GET")
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun `Consume key with body`() {
        mock.put("/key/consume") {
            header(API_KEY_HEADER, ADMIN_KEY)

            val key = KeyConsume("/trips", HttpMethod.GET)
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(key)
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun `User consuming exceeds quota`() {
        val request = {
            mock.put("/key/consume") {
                header(API_KEY_HEADER, USER_KEY)
                header(ORIGINAL_PATH_HEADER, "/trips")
                header(ORIGINAL_METHOD_HEADER, "GET")
            }
        }

        // each request consumes 3 uses, user has a quota of 10
        for (i in 1..3) {
            request()
                .andExpect {
                    status {
                        isOk()
                    }
                }
        }

        request()
            .andExpect {
                status {
                    isForbidden()
                }
            }
    }

}
