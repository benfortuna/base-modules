/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2009, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mnode.base.commons.osgi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.mnode.base.commons.ServiceNotAvailableException;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author fortuna
 *
 */
public class ServiceProxy {

    /**
     * @param <T> the service type
     * @param classLoader the classloader for the proxy
     * @param serviceClass the service class to proxy
     * @param context the bundle context use to locate the underlying service
     * @return a new proxy for the specified service type
     */
    @SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(ClassLoader classLoader, Class<T> serviceClass, BundleContext context) {
        return (T) Proxy.newProxyInstance(classLoader, new Class<?>[] { serviceClass},
                new ServiceInvocationHandler(serviceClass, context));
    }
    
    private static class ServiceInvocationHandler implements InvocationHandler {
        
        private final ServiceTracker tracker;
        
        public ServiceInvocationHandler(Class<?> serviceClass, BundleContext context) {
            tracker = new ServiceTracker(context, serviceClass.getName(), null);
            tracker.open();
        }
        
        /**
         * {@inheritDoc}
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final Object service = tracker.getService();
            if (service == null) {
                throw new ServiceNotAvailableException();
            }
            return method.invoke(service, args);
        }
    }
}
