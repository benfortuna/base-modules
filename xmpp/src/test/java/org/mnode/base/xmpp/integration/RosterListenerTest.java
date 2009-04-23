/**
 * 
 */
package org.mnode.base.xmpp.integration;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * @author fortuna
 *
 */
public class RosterListenerTest extends AbstractXmppTest {

	private static final Log LOG = LogFactory.getLog(RosterListenerTest.class);
	
	public void testRegisterRosterListener() throws XMPPException {

		RosterListener listener = new RosterListener() {
			@Override
			public void entriesAdded(Collection<String> arg0) {
				LOG.info("Roster entries added: " + arg0);
			}
			@Override
			public void entriesDeleted(Collection<String> arg0) {
				LOG.info("Roster entries deleted: " + arg0);
			}
			@Override
			public void entriesUpdated(Collection<String> arg0) {
				LOG.info("Roster entries updated: " + arg0);
			}
			@Override
			public void presenceChanged(Presence arg0) {
				LOG.info("Presence changed: " + arg0.getFrom() + ", " + arg0.getStatus());
			}
		};
		bundleContext.registerService(RosterListener.class.getName(), listener, null);

		XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
		connection.disconnect();
	}

}
