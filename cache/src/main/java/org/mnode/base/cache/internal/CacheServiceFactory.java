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

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author fortuna
 *
 */
public class CacheServiceFactory {

	private CacheManager cacheManager;

	/*
	public Object getService(Bundle bundle, ServiceRegistration registration) {
		cacheManager = new CacheManager();
		String cacheId = bundle.getSymbolicName() + registration.getReference().getProperty(CacheDescriptor.KEY_CACHE_ID);
		Cache cache = cacheManager.getCache(cacheId);
		if (cache == null) {
			// initialise cache..
			int maxElements = (Integer) registration.getReference().getProperty(CacheDescriptor.KEY_MAX_ELEMENTS);
			boolean persistent = (Boolean) registration.getReference().getProperty(CacheDescriptor.KEY_PERSISTENT);
			cache = createCache(cacheId, maxElements, persistent);
			cacheManager.addCache(cache);
//				cache = cacheManager.getCache(descriptor.getId());
		}
		return cache;
	}
	*/

	/*
	public void ungetService(Bundle bundle, ServiceRegistration registration, Object service) {
		// TODO Auto-generated method stub

	}
	*/
	
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

	public final void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
