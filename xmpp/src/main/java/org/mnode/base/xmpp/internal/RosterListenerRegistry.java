package org.mnode.base.xmpp.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;

public class RosterListenerRegistry implements ConnectionCreationListener {
	
//	private static final Log LOG = LogFactory.getLog(RosterListenerRegistry.class);
	
	private List<XMPPConnection> connections;
	
	private List<RosterListener> rosterListeners;

	public RosterListenerRegistry() {
		connections = new ArrayList<XMPPConnection>();
		rosterListeners = new ArrayList<RosterListener>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
		connections.add(connection);
		/* Roster always null for created connections..
		if (connection.getRoster() != null) {
			for (RosterListener listener : rosterListeners) {
				connection.getRoster().addRosterListener(listener);
			}
		}
		*/
	}
	
	public void addRosterListener(RosterListener rosterListener, Map<?, ?> props) {
		rosterListeners.add(rosterListener);
		for (XMPPConnection connection : connections) {
			if (connection.getRoster() != null) {
				connection.getRoster().addRosterListener(rosterListener);
			}
		}
	}
	
	public void removeRosterListener(RosterListener rosterListener, Map<?, ?> props) {
		rosterListeners.remove(rosterListener);
		for (XMPPConnection connection : connections) {
			if (connection.getRoster() != null) {
				connection.getRoster().removeRosterListener(rosterListener);
			}
		}
	}
}
