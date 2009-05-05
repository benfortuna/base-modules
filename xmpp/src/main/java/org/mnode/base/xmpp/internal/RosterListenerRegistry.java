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
