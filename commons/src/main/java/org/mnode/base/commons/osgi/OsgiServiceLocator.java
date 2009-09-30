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

import java.util.HashMap;
import java.util.Map;

import org.mnode.base.commons.ServiceLocator;
import org.mnode.base.commons.ServiceName;
import org.mnode.base.commons.ServiceNotAvailableException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author fortuna
 *
 */
public class OsgiServiceLocator implements ServiceLocator {

    private final BundleContext context;
    
    private final Map<ServiceName, ServiceTracker> serviceTrackers;
    
    /**
     * @param context the bundle context in which to find services
     */
    public OsgiServiceLocator(BundleContext context) {
        this.context = context;
        serviceTrackers = new HashMap<ServiceName, ServiceTracker>();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T findService(ServiceName name) throws ServiceNotAvailableException {
        ServiceTracker tracker = serviceTrackers.get(name);
        if (tracker == null) {
            synchronized (serviceTrackers) {
                tracker = serviceTrackers.get(name);
                if (tracker == null) {
                    try {
                        final Filter filter = context.createFilter(name.getFilter());
                        tracker = new ServiceTracker(context, filter, null);
                        tracker.open();
                        serviceTrackers.put(name, tracker);
                    } catch (InvalidSyntaxException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        }
        final T service = (T) tracker.getService();
        if (service == null) {
            throw new ServiceNotAvailableException("Service matching [" + name.getFilter() + "] not found.");
        }
        return service;
    }

    /**
     * Clean up resources.
     */
    public void reset() {
        for (ServiceTracker tracker : serviceTrackers.values()) {
            tracker.close();
        }
        serviceTrackers.clear();
    }
}
