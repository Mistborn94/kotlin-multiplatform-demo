import kotlin.js.JsName

interface Logger {

    @JsName("info")
    fun info(message: String)
}