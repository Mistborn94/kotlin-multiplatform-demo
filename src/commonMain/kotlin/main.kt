//You can have standalone functions
fun main(args: Array<String>) {

    LoggerFactory.getLogger().info("Hello World!")

    //Common Module contains pure kotlin code
    //The following will not compile
    //console.info("Test")
    //println(LocalDateTime.now())

}