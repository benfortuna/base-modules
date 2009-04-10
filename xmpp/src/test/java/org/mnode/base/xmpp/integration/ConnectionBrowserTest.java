/**
 * 
 */
package org.mnode.base.xmpp.integration;

import org.mnode.base.xmpp.ConnectionBrowser;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ConnectionBrowserTest extends AbstractConfigurableBundleCreatorTests {

	public void testGetConnectionBrowser() {
		ServiceReference reference = bundleContext.getServiceReference(ConnectionBrowser.class.getName());
		ConnectionBrowser connectionBrowser = (ConnectionBrowser) bundleContext.getService(reference);
		assertNotNull(connectionBrowser);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT" };
	}

}
