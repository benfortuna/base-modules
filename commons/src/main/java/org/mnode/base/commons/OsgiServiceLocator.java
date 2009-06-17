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

package org.mnode.base.commons;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author fortuna
 *
 */
public class OsgiServiceLocator implements ServiceLocator {

    private final BundleContext context;
    
    private final Map<Class<?>, ServiceTracker> serviceTrackers;
    
    /**
     * @param context the bundle context in which to find services
     */
    public OsgiServiceLocator(BundleContext context) {
        this.context = context;
        serviceTrackers = new HashMap<Class<?>, ServiceTracker>();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T findService(Class<T> type) {
        ServiceTracker tracker = serviceTrackers.get(type);
        if (tracker == null) {
            synchronized (serviceTrackers) {
                tracker = serviceTrackers.get(type);
                if (tracker == null) {
                    tracker = new ServiceTracker(context, type.getName(), null);
                    tracker.open();
                    serviceTrackers.put(type, tracker);
                }
            }
        }
        return (T) tracker.getService();
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
