package com.mvpotter.osgi

import org.osgi.framework.Bundle
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference

/**
 * @author mvpotter
 * @since 23/02/17
 */
class HostActivator: BundleActivator {

    private var bundleContext: BundleContext? = null

    override fun start(context: BundleContext?) {
        bundleContext = context
    }

    override fun stop(context: BundleContext?) {
        bundleContext = null
    }

    fun <T> getService(reference: ServiceReference<T>): T {
        return bundleContext!!.getService(reference)
    }

    fun installBundle(location: String): Bundle {
        return bundleContext!!.installBundle(location)
    }

    fun <T> findServices(clazz: Class<T>, filter: String? = null): Collection<ServiceReference<T>> {
        return bundleContext!!.getServiceReferences(clazz, filter)
    }

    fun getBundles(): Array<Bundle> {
        return bundleContext!!.bundles
    }

}