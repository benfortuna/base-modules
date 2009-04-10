/**
 * 
 */
package org.mnode.base.mdns.integration;

import org.mnode.base.mdns.ServiceBrowser;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ServiceBrowserTest extends AbstractConfigurableBundleCreatorTests {

	public void testGetServiceBrowser() {
		ServiceReference reference = bundleContext.getServiceReference(ServiceBrowser.class.getName());
		ServiceBrowser serviceBrowser = (ServiceBrowser) bundleContext.getService(reference);
		assertNotNull(serviceBrowser);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, mdns, 0.0.1-SNAPSHOT" };
	}
	
//	protected boolean createManifestOnlyFromTestClass() {
//		return false;
//	}
}
