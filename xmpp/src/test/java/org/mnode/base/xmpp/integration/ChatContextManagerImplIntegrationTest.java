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
package org.mnode.base.xmpp.integration;

import java.io.IOException;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.mnode.base.commons.osgi.OsgiServiceLocator;
import org.mnode.base.commons.ServiceLocator;
import org.mnode.base.commons.ServiceNotAvailableException;
import org.mnode.base.config.UnsupportedValueConversionException;
import org.mnode.base.xmpp.ChatContext;
import org.mnode.base.xmpp.ChatContextManager;

public class ChatContextManagerImplIntegrationTest extends AbstractXmppIntegrationTest {

    public void testGetChatContext() throws XMPPException, ServiceNotAvailableException,
        IOException, UnsupportedValueConversionException {

//        ServiceTracker tracker = new ServiceTracker(bundleContext, ChatContextManager.class.getName(), null);
//        tracker.open();
        ServiceLocator serviceLocator = new OsgiServiceLocator(bundleContext);
        
//        final ChatContextManager contextManager = (ChatContextManager) tracker.getService();
        final ChatContextManager contextManager = serviceLocator.findService(ChatContextManager.class);
        assertNotNull(contextManager);

        final XMPPConnection connection = newConnection();

        ChatManagerListener listener = new ChatManagerListener() {
            @Override
            public void chatCreated(Chat arg0, boolean arg1) {
                ChatContext context = contextManager.getContext(arg0);
                assertNotNull(context);
                assertEquals(connection, context.getConnection());
            }
        };
        bundleContext.registerService(ChatManagerListener.class.getName(), listener, null);

        connection.disconnect();
    }

}
