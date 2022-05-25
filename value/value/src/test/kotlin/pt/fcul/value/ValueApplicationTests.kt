package pt.fcul.value

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles


@Import(AppTestConfig::class)
@SpringBootTest("spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("mocked-clients-config")
@AutoConfigureMockMvc
class ValueApplicationTests {

    @Test
    fun contextLoads() {
    }

}