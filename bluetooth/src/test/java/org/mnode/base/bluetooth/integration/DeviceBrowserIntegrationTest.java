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
package org.mnode.base.bluetooth.integration;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mnode.base.bluetooth.DeviceBrowser;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class DeviceBrowserIntegrationTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(DeviceBrowserIntegrationTest.class);
	
	public void testGetServiceBrowser() throws IOException {
		ServiceReference reference = bundleContext.getServiceReference(DeviceBrowser.class.getName());
		DeviceBrowser deviceBrowser = (DeviceBrowser) bundleContext.getService(reference);
		assertNotNull(deviceBrowser);
		
		for (RemoteDevice device : deviceBrowser.getDevices()) {
			LOG.info("Device [" + device.getFriendlyName(true) + "] found.");
		}
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, org.mnode.base.bluetooth, 0.0.1-SNAPSHOT"};
//				"org.mnode.base, bluetooth-linux, 0.0.1-SNAPSHOT"};
	}

}
