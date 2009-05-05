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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

public class ConnectionListenerRegistry implements ConnectionCreationListener {
	
	private static final Log LOG = LogFactory.getLog(ConnectionListenerRegistry.class);
	
	private List<XMPPConnection> connections;
	
	private List<ConnectionListener> listeners;
	
	public ConnectionListenerRegistry() {
		this.connections = new ArrayList<XMPPConnection>();
		this.listeners = new ArrayList<ConnectionListener>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
		connections.add(connection);
		
		for (ConnectionListener listener : listeners) {
			connection.addConnectionListener(listener);
		}
	}

	public void addConnectionListener(ConnectionListener listener, Map<?, ?> props) {
		listeners.add(listener);
		for (XMPPConnection connection : connections) {
			connection.addConnectionListener(listener);
		}
	}

	public void removeConnectionListener(ConnectionListener listener, Map<?, ?> props) {
		listeners.remove(listener);
		for (XMPPConnection connection : connections) {
			try {
				connection.removeConnectionListener(listener);
			}
			catch (NullPointerException npe) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Connection listener removal failed: " + npe);
				}
			}
		}
	}
}
