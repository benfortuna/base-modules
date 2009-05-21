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
package org.mnode.base.mdns.internal;

import java.io.IOException;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceTypeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mnode.base.commons.AbstractPubSubRegistry;

public class ServiceTypeListenerRegistry extends AbstractPubSubRegistry<JmDNS, ServiceTypeListener> {

	private static final Log LOG = LogFactory.getLog(ServiceTypeListenerRegistry.class);
	
	@Override
	protected void subscribe(JmDNS publisher, ServiceTypeListener subscriber, Map<String, String> props) {
		try {
			publisher.addServiceTypeListener(subscriber);
		} catch (IOException e) {
			LOG.error("Error subscribing", e);
		}
	}
	
	@Override
	protected void unsubscribe(JmDNS publisher, ServiceTypeListener subscriber, Map<String, String> props) {
		publisher.removeServiceTypeListener(subscriber);
	}
}
