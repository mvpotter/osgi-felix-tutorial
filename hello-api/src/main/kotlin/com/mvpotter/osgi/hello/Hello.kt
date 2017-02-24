package com.mvpotter.osgi.hello

/**
 * @author mvpotter
 * @since 23/02/17
 */
interface Hello {

    /**
     * Returns a message like: "Hello $user_name".
     * @param name the name
     * *
     * @return the hello message
     */
    fun sayHello(name: String): String

}