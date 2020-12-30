package cloud.practice.trigger.time

import com.microsoft.azure.functions.ExecutionContext
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.TimerTrigger
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function
class TimeTriggerHandler : AzureSpringBootRequestHandler<User, Unit>() {
    @FunctionName("testTimer")
    fun execute(
            @TimerTrigger(name = "jobExpirationTrigger", schedule = "0 */1 * * * *")
            timerInfo: String,
            ctx: ExecutionContext
    ): Unit {
        val user = User("Ranjeet Divity")
        ctx.logger.info("JobExpirerFunction : execute() :: Greeting user name: " + user.name)
        return   handleRequest(user,ctx)
    }

}


@Configuration
class FunctionConfiguration{

    @Bean
    fun testTimer(): Function<User, Unit>? {
        return Function<User, Unit> { user: User -> println("Welcome, " + user.name) }
    }
}