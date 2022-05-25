package ul.fc.mei.cn.server.grpc

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.repository.BusinessMongoDBRepository

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BusinessServiceTest {

    protected val objectMapper: ObjectMapper = jacksonObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)!!

    @Autowired
    lateinit var dataRepo: BusinessMongoDBRepository

    @Autowired
    lateinit var mock: MockMvc


    @BeforeAll
    fun initialization() {
        val business = Business("0", "Some Bizz", "Some Street n1", "New York", 70.0, 150.0)
        val otherBiz = Business("1", "Some Other Bizz", "Some Street n1", "New York", 70.0, 150.0)
        dataRepo.addBusiness(business)
        dataRepo.addBusiness(otherBiz)
    }


    @Test
    fun `Non existing resource should not exist`() {
        mock.get("/business/-1")
            .andExpect {
                status {
                    isNotFound()
                }
            }
    }

    @Test
    fun `Existing resource should be returned`() {
        mock.get("/business/0")
            .andExpect {
                status {
                    isOk()

                }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                    jsonPath("$.id") {
                        value("0")
                    }
                }
            }
    }

    @Test
    fun `Attempting to update non existing resource should lead to error`() {
        mock.put("/business/-1") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                Business(
                    "-3",
                    "Some Other Updated Bizz",
                    "Some Street n1",
                    "New York",
                    70.0,
                    150.0
                )
            )
        }.andExpect {
            status {
                isNotFound()
            }
        }
    }

    @Test
    fun `Attempting to delete non existing resource should lead to error`() {
        mock.delete("/business/-1")
            .andExpect {
                status {
                    isNotFound()
                }
            }
    }

    @Test
    fun `Attempting to update with invalid coordinates should lead to error`() {
        mock.put("/business/0") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                Business(
                    "-3",
                    "Some Other Updated Bizz",
                    "Some Street n1",
                    "New York",
                    10000.0,
                    9999.0
                )
            )
        }.andExpect {
            status {
                isBadRequest()
            }
        }
    }

    @Test
    fun `Attempting to update an existing resource should be successful`() {
        mock.get("/business/1")
            .andExpect {
                status {
                    isOk()
                }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                    jsonPath("$.name") {
                        value("Some Other Bizz")
                    }
                }
            }

        mock.put("/business/1") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                Business(
                    "1",
                    "Some Other Updated Bizz",
                    "Some Street n1",
                    "New York",
                    70.0,
                    150.0
                )
            )
        }.andExpect {
            status {
                isOk()
            }
            content {
                contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                jsonPath("$.name") {
                    value("Some Other Updated Bizz")
                }
            }
        }
    }

    @AfterAll
    fun cleanUp() {
        dataRepo.deleteBusiness("0")
        dataRepo.deleteBusiness("1")
        println("Clean up executed")
    }
}