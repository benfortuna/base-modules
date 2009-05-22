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

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.mnode.base.commons.AbstractPubSubRegistry;

public class RosterListenerRegistry extends AbstractPubSubRegistry<XMPPConnection, RosterListener> implements ConnectionCreationListener {
	
	public RosterListenerRegistry() {
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
	    registerPublisher(connection, null);
	}
	
	@Override
	protected void subscribe(XMPPConnection publisher,
	        RosterListener subscriber, Map<String, ?> properties) {
	    if (publisher.getRoster() != null) {
	        publisher.getRoster().addRosterListener(subscriber);
	    }
	}
	
	@Override
	protected void unsubscribe(XMPPConnection publisher,
	        RosterListener subscriber, Map<String, ?> properties) {
	    if (publisher.getRoster() != null) {
	        publisher.getRoster().removeRosterListener(subscriber);
	    }
	}
}
