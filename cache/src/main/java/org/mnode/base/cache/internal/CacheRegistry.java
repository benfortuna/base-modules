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
package org.mnode.base.cache.internal;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.mnode.base.cache.CacheDescriptor;

/**
 * @author Ben
 *
 */
public class CacheRegistry {

	private CacheManager cacheManager;
	
//	private BundleContext bundleContext;
	
	private Set<CacheDescriptor> descriptors;
	
//	private Map<CacheDescriptor, ServiceRegistration> registrations;
	
	/**
	 * Default constructor.
	 */
	public CacheRegistry() {
		descriptors = new HashSet<CacheDescriptor>();
//		registrations = new HashMap<CacheDescriptor, ServiceRegistration>();
	}
	
	/**
	 * @param descriptor
	 */
	public void register(CacheDescriptor descriptor, Map<?, ?> props) {
		descriptors.add(descriptor);
		
		Cache cache = cacheManager.getCache(descriptor.getId());
		if (cache == null) {
			// initialise cache..
			cache = createCache(descriptor.getId(),
					descriptor.getMaxElements(), descriptor.isPersistent());
			cacheManager.addCache(cache);
//				cache = cacheManager.getCache(descriptor.getId());
		}

		Dictionary<String, Object> serviceProps = new Hashtable<String, Object>();
		serviceProps.put(CacheDescriptor.KEY_CACHE_ID, descriptor.getId());
		serviceProps.put(CacheDescriptor.KEY_MAX_ELEMENTS, descriptor.getMaxElements());
		serviceProps.put(CacheDescriptor.KEY_PERSISTENT, descriptor.isPersistent());
		
//		ServiceRegistration registration = bundleContext.registerService(
//				Cache.class.getName(), cache, serviceProps);
//		registrations.put(descriptor, registration);
	}
	
	/**
	 * @param id
	 * @param maxElements
	 * @param persistent
	 * @return
	 */
	private Cache createCache(String name, int maxElements, boolean persistent) {
		return new Cache(name, maxElements, null, persistent, null, true, 0, 0,
				persistent, 360000, null, null, 1000000);
	}
	
	/**
	 * @param descriptor
	 */
	public void unregister(CacheDescriptor descriptor, Map<?, ?> props) {
		descriptors.remove(descriptor);
		
//		ServiceRegistration registration = registrations.get(descriptor);
//		if (registration != null) {
//			registration.unregister();
//			registrations.remove(registration);
//		}
	}
	
	/**
	 * Declarative services activation.
	 * @param bundleContext
	 */
	public void activate() {
//		this.context = context.getBundleContext();
		
		// register deferred registrations..
		for (CacheDescriptor descriptor : descriptors) {
//			if (registrations.get(descriptor) == null) {
//				register(descriptor, null);
//			}
		}
	}
	
	/**
	 * Declarative services deactivation.
	 * @param bundleContext
	 */
	protected void deactivate() {
//        cacheManager.shutdown();
		
//		for (ServiceRegistration registration : registrations.values()) {
//			registration.unregister();
//		}
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public final void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
//	public void setBundleContext(BundleContext bundleContext) {
//		this.bundleContext = bundleContext;
//	}
}
