/**
 * 
 */
package org.mnode.base.bluetooth.integration;

import org.mnode.base.bluetooth.DeviceBrowser;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class DeviceBrowserTest extends AbstractConfigurableBundleCreatorTests {

	public void testGetServiceBrowser() {
		ServiceReference reference = bundleContext.getServiceReference(DeviceBrowser.class.getName());
		DeviceBrowser deviceBrowser = (DeviceBrowser) bundleContext.getService(reference);
		assertNotNull(deviceBrowser);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, bluetooth, 0.0.1-SNAPSHOT"};
//				"org.mnode.base, bluetooth-linux, 0.0.1-SNAPSHOT"};
	}

}
