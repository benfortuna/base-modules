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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.mnode.base.commons.AbstractPubSubRegistry;

public class ConnectionListenerRegistry extends AbstractPubSubRegistry<XMPPConnection, ConnectionListener> implements ConnectionCreationListener {
	
	private static final Log LOG = LogFactory.getLog(ConnectionListenerRegistry.class);
	
	public ConnectionListenerRegistry() {
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
	    registerPublisher(connection, null);
	}

	@Override
	protected void subscribe(XMPPConnection publisher,
	        ConnectionListener subscriber, Map<String, ?> properties) {
	    publisher.addConnectionListener(subscriber);
	}
	
	@Override
	protected void unsubscribe(XMPPConnection publisher,
	        ConnectionListener subscriber, Map<String, ?> properties) {
	    try {
	        publisher.removeConnectionListener(subscriber);
	    } catch (NullPointerException npe) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Connection listener removal failed: " + npe);
            }
        }
	}
}
