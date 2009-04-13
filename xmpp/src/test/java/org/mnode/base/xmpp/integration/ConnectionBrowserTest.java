/**
 * 
 */
package org.mnode.base.xmpp.integration;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.mnode.base.xmpp.ConnectionBrowser;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ConnectionBrowserTest extends AbstractConfigurableBundleCreatorTests {

	public void testGetConnectionBrowser() throws XMPPException {
		ServiceReference reference = bundleContext.getServiceReference(ConnectionBrowser.class.getName());
//		ConnectionConfiguration config = new ConnectionConfiguration("jabber.org.au", 5222);
		XMPPConnection connection = new XMPPConnection("jabber.org.au");
		connection.connect();
//		connection.login("benfortuna", "blw550");
		ConnectionBrowser connectionBrowser = (ConnectionBrowser) bundleContext.getService(reference);
		assertNotNull(connectionBrowser);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT" };
	}

}
