/**
 * 
 */
package org.mnode.base.cache.internal;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.mnode.base.cache.CacheDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * @author fortuna
 *
 */
public class CacheServiceFactory implements ServiceFactory {

	private CacheManager cacheManager;

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

	public void ungetService(Bundle bundle, ServiceRegistration registration, Object service) {
		// TODO Auto-generated method stub

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

	public final void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
