package com.mvpotter.osgi

import com.mvpotter.osgi.hello.Hello
import org.apache.felix.framework.Felix
import org.apache.felix.framework.cache.BundleCache
import org.apache.felix.framework.util.FelixConstants
import org.apache.felix.framework.util.StringMap
import org.osgi.framework.Bundle
import org.osgi.framework.Constants

/**
 * @author mvpotter
 * @since 24/02/17
 */
class HostApplication {

    var felix: Felix? = null
    val activator = HostActivator()

    init {

        val configMap = StringMap()
        configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES,
                      "org.osgi.framework; version=1.8.0")
        configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA,
                      "kotlin; version=1.0.6," +
                      "kotlin.jvm.internal; version=1.0.6," +
                      "com.mvpotter.osgi.hello; version=1.0.0")
        configMap.put(BundleCache.CACHE_ROOTDIR_PROP, "cache")

        val list = listOf(activator)
        configMap.put(FelixConstants.SYSTEMBUNDLE_ACTIVATORS_PROP, list)

        try {
            felix = Felix(configMap)
            felix?.start()
        } catch (ex: Exception) {
            System.err.println("Could not create framework: " + ex)
            ex.printStackTrace()
            System.exit(-1)
        }
    }

    fun registerBundle(location: String): Bundle {
        val bundle = activator.installBundle(location)
        bundle.start()
        return bundle
    }

    fun sayHello(language: String, name: String): String {
        val helloServicesRefs = activator.findServices(Hello::class.java, "(Language=$language)")
        if (helloServicesRefs.isNotEmpty()) {
            return activator.getService(helloServicesRefs.first()).sayHello(name)
        } else {
            throw UnsupportedOperationException("Service not found")
        }
    }

    fun shutdownApplication() {
        felix?.stop()
    }

}