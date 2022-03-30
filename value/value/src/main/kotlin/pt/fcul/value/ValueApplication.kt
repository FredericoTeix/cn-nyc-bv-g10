package pt.fcul.value

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ValueApplication

fun main(args: Array<String>) {
	runApplication<ValueApplication>(*args)
}
