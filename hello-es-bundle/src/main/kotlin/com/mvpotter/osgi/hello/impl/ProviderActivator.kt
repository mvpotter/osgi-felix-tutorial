package com.mvpotter.osgi.hello.impl

import com.mvpotter.osgi.hello.Hello
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceRegistration
import java.util.*

/**
 * @author mvpotter
 * @since 23/02/17
 */
class ProviderActivator : BundleActivator {
    private var registration: ServiceRegistration<*>? = null

    @Throws(Exception::class)
    override fun start(bundleContext: BundleContext) {
        val params = Hashtable<String, String>()
        params["Language"] = "Spanish"
        registration = bundleContext.registerService(
                Hello::class.java,
                HelloImpl(),
                params)
    }

    @Throws(Exception::class)
    override fun stop(bundleContext: BundleContext) {
        registration!!.unregister()
    }
}