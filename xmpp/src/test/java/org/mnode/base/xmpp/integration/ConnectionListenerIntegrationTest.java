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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.mnode.base.config.UnsupportedValueConversionException;

public class ConnectionListenerIntegrationTest extends AbstractXmppIntegrationTest {

    private static final Log LOG = LogFactory.getLog(ConnectionListenerIntegrationTest.class);

    public void testRegisterConnectionListener() throws XMPPException, IOException,
        UnsupportedValueConversionException {
        
        ConnectionListener listener = new ConnectionListener() {
            @Override
            public void connectionClosed() {
                LOG.info("Connection closed");
            }

            @Override
            public void connectionClosedOnError(Exception arg0) {
                LOG.info("Connection closed - error: " + arg0);
            }

            @Override
            public void reconnectingIn(int arg0) {
                LOG.info("Reconnecting in: " + arg0);
            }

            @Override
            public void reconnectionFailed(Exception arg0) {
                LOG.info("Reconnection failed - error: " + arg0);
            }

            @Override
            public void reconnectionSuccessful() {
                LOG.info("Reconnection successful");
            }
        };

        bundleContext.registerService(ConnectionListener.class.getName(), listener, null);

        XMPPConnection connection = newConnection();

        connection.disconnect();
    }

}
