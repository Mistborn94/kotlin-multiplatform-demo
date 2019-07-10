import java.time.LocalDate
import java.time.LocalDateTime

actual object LoggerFactory {

    actual fun getLogger(): Logger {
        return JavaLogger()
    }
}

class JavaLogger: Logger {
    override fun info(message: String) {
        println("Java | ${LocalDateTime.now()} | $message")
    }
}