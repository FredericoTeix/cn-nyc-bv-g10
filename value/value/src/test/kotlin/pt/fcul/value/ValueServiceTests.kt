package pt.fcul.value

import java.time.LocalDateTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ExperimentalCoroutinesApi
@Import(AppTestConfig::class)
@SpringBootTest("spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("mocked-clients-config")
class ValueServiceTests {

    @Autowired
    lateinit var service: ValueService

    @Test
    fun testService() {
        val value = service.getBusinessValue("0", LocalDateTime.now(), LocalDateTime.now())
        assertEquals("0", value.id)
        assertEquals(0, value.value)
    }

}