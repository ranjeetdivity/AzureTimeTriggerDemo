package cloud.practice.trigger.time

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AzureTimeTriggerDemoApplication

fun main(args: Array<String>) {
    runApplication<AzureTimeTriggerDemoApplication>(*args)
}
