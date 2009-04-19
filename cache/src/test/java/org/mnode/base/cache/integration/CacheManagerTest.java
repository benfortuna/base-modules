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
		return new String[] { "org.mnode.base, cache, 0.0.1-SNAPSHOT",
				"org.springframework, spring-context-support, 2.5.5",
				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}

}
