package com.mvpotter.osgi.hello.impl

import com.mvpotter.osgi.hello.Hello

/**
 * @author mvpotter
 * @since 23/02/17
 */
class HelloImpl : Hello {

    override fun sayHello(name: String): String {
        return "¡Hola $name!"
    }

}