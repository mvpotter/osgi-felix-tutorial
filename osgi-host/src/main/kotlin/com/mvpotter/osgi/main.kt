package com.mvpotter.osgi

fun main(args: Array<String>) {
    val app = HostApplication()
    app.registerBundle("file:hello-en-bundle/target/hello-en-bundle-1.0.0.jar")
    app.registerBundle("file:hello-es-bundle/target/hello-es-bundle-1.0.0.jar")

    val name = "OSGi"
    sayHello(app, "English", name)
    sayHello(app, "Spanish", name)
    sayHello(app, "French", name)

    app.shutdownApplication()
}

fun sayHello(app: HostApplication, language: String, name: String) {
    println("--- $language --")
    println()
    try {
        println(app.sayHello(language, name))
    } catch (e: UnsupportedOperationException) {
        println(e.message)
    }
    println()
}