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
package org.mnode.base.irc.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mnode.base.irc.ConnectionDescriptor;
import org.mnode.base.irc.MessageHandler;
import org.osgi.framework.ServiceRegistration;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class MessageHandlerIntegrationTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(MessageHandlerIntegrationTest.class);
	
	public void testMessageHandling() {
		MessageHandler testHandler = new MessageHandler() {
			public void handleMessage(String channel, String sender,
					String login, String hostname, String message) {
				
				LOG.info("Received message: " + message);
			}
		};
		
		ConnectionDescriptor connection = new ConnectionDescriptor();
		connection.setHostname("irc.freenode.net");
		connection.setChannel("#pircbot");
		connection.setName("elbento");
		ServiceRegistration connectionReg = bundleContext.registerService(ConnectionDescriptor.class.getName(), connection, null);
		
		ServiceRegistration handlerReg = bundleContext.registerService(MessageHandler.class.getName(), testHandler, null);
		
		connectionReg.unregister();
		handlerReg.unregister();
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, org.mnode.base.irc, 0.0.1-SNAPSHOT",
				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}
}
