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
package org.mnode.base.cache.integration;

import net.sf.ehcache.CacheManager;

import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class CacheManagerTest extends AbstractConfigurableBundleCreatorTests {
	
	public void testGetCacheManager() throws InterruptedException {
//		CacheDescriptor descriptor = new CacheDescriptor();
//		descriptor.setId("testCache");
//		Dictionary<String, Object> props = new Hashtable<String, Object>();
//		props.put(CacheDescriptor.KEY_CACHE_ID, "testCache");
//		props.put(CacheDescriptor.KEY_MAX_ELEMENTS, 1000);
//		props.put(CacheDescriptor.KEY_PERSISTENT, false);

//		ServiceTracker tracker = new ServiceTracker(bundleContext, Cache.class.getName(), null);

//		ServiceRegistration reg = bundleContext.registerService(CacheDescriptor.class.getName(), descriptor, null);
		
//		Cache cache = (Cache) tracker.waitForService(10000);
		ServiceReference ref = bundleContext.getServiceReference(CacheManager.class.getName());
		CacheManager cacheManager = (CacheManager) bundleContext.getService(ref);
//		assertEquals("testCache", cache.getName());
		assertNotNull(cacheManager);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, org.mnode.base.cache, 0.0.1-SNAPSHOT",
				"org.springframework, spring-context-support, 2.5.5",
				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}

}
