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
package org.mnode.base.log.integration;

import net.sf.ehcache.Cache;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class LogCacheManagerTest extends AbstractConfigurableBundleCreatorTests {
	
	public void testGetDebugCache() throws InvalidSyntaxException {
		ServiceReference[] refs = bundleContext.getServiceReferences(Cache.class.getName(), "(cacheId=debugLog)");
		assertEquals(1, refs.length);
		
		Cache cache = (Cache) bundleContext.getService(refs[0]);
		assertNotNull(cache);
		
		ServiceReference logServiceRef = bundleContext.getServiceReference(LogService.class.getName());
		LogService logService = (LogService) bundleContext.getService(logServiceRef);
		logService.log(LogService.LOG_DEBUG, "This is a test");
		
		assertEquals(1, cache.getSize());
		
//		Logger logger = Logger.getLogger(LogCacheManagerTest.class);
//		logger.debug("This is another test");
//		assertEquals(2, cache.getSize());
		
//		Log log = LogFactory.getLog(LogCacheManagerTest.class);
//		log.debug("This is another test");
//		assertEquals(3, cache.getSize());
	}

	/*
	@Override
	protected String[] getTestFrameworkBundlesNames() {
		List<String> testFrameworkBundleNames = new ArrayList<String>();
		for (String bundleName : super.getTestFrameworkBundlesNames()) {
			if (!bundleName.contains("log") && !bundleName.contains("slf4j")) {
				testFrameworkBundleNames.add(bundleName);
			}
		}
		return testFrameworkBundleNames.toArray(new String[testFrameworkBundleNames.size()]);
	}
	*/
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, base-log, 0.0.1-SNAPSHOT",
				"org.springframework, spring-context-support, 2.5.5",
				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3",
				"org.apache.felix, org.osgi.compendium, 1.2.0",
				"org.eclipse.equinox, log, 1.0.100-v20070226"};
	}

}
