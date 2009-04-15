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
public class DeviceBrowserTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(DeviceBrowserTest.class);
	
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
		return new String[] { "org.mnode.base, bluetooth, 0.0.1-SNAPSHOT"};
//				"org.mnode.base, bluetooth-linux, 0.0.1-SNAPSHOT"};
	}

}
