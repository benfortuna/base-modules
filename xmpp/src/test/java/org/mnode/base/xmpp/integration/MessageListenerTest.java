/**
 * 
 */
package org.mnode.base.xmpp.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class MessageListenerTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(MessageListenerTest.class);
	
	public void testRegisterMessageListener() throws XMPPException {

		MessageListener listener = new MessageListener() {
			@Override
			public void processMessage(Chat arg0, Message arg1) {
				LOG.info("Message: " + arg0.getParticipant() + ", " + arg1.getBody());
			}
		};
		bundleContext.registerService(MessageListener.class.getName(), listener, null);

		XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
		connection.disconnect();
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT" };
	}

}
