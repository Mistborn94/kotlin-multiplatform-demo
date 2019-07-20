import kotlin.browser.document
import kotlin.js.Date

actual object LoggerFactory {

    actual fun getLogger(): Logger {
        return JsLogger()
    }

}

class JsLogger: Logger {
    override fun info(message: String) {
        console.info("Js | ${Date().toISOString()} | $message")
        document.write("<p>Js | ${Date().toISOString()} | <em>$message</em></p>")
    }
}