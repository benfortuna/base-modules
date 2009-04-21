package org.mnode.base.xmpp.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class ConnectionListenerTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(ConnectionListenerTest.class);

	public void testRegisterConnectionListener() throws XMPPException {
		ConnectionListener listener = new ConnectionListener() {
			@Override
			public void connectionClosed() {
				LOG.info("Connection closed");
			}
			@Override
			public void connectionClosedOnError(Exception arg0) {
				LOG.info("Connection closed - error: " + arg0);
			}
			@Override
			public void reconnectingIn(int arg0) {
				LOG.info("Reconnecting in: " + arg0);
			}
			@Override
			public void reconnectionFailed(Exception arg0) {
				LOG.info("Reconnection failed - error: " + arg0);
			}
			@Override
			public void reconnectionSuccessful() {
				LOG.info("Reconnection successful");
			}
		};
		
		bundleContext.registerService(ConnectionListener.class.getName(), listener, null);

		XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
		
		connection.disconnect();
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT",
			"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}

}
