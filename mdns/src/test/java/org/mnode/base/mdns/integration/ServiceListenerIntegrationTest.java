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
/**
 * 
 */
package org.mnode.base.mdns.integration;

import java.util.Hashtable;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;
import org.springframework.osgi.test.platform.Platforms;

/**
 * @author fortuna
 *
 */
public class ServiceListenerIntegrationTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(ServiceListenerIntegrationTest.class);
	
	public void testGetServiceBrowser() {
//		ServiceReference reference = bundleContext.getServiceReference(ServiceBrowser.class.getName());
//		ServiceBrowser serviceBrowser = (ServiceBrowser) bundleContext.getService(reference);
//		assertNotNull(serviceBrowser);
		ServiceTypeListener typeListener = new ServiceTypeListener() {
			@Override
			public void serviceTypeAdded(ServiceEvent arg0) {
				LOG.info("Service type added: " + arg0.getName());
			}
		};
		bundleContext.registerService(ServiceTypeListener.class.getName(), typeListener, null);
		
		ServiceListener listener = new ServiceListener() {
			@Override
			public void serviceAdded(ServiceEvent arg0) {
				LOG.info("Service added: " + arg0.getName());
			}
			@Override
			public void serviceRemoved(ServiceEvent arg0) {
				LOG.info("Service remove: " + arg0.getName());
			}
			@Override
			public void serviceResolved(ServiceEvent arg0) {
				LOG.info("Service resolved: " + arg0.getName());
			}
		};
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put("serviceType", "_tcp.0.0.1");
		bundleContext.registerService(ServiceListener.class.getName(), listener, props);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, base-mdns, 0.0.1-SNAPSHOT",
				"org.mnode.base, base-commons, 0.0.1-SNAPSHOT" };
	}
	

	@Override
	protected String getPlatformName() {
		return Platforms.FELIX;
	}
}
