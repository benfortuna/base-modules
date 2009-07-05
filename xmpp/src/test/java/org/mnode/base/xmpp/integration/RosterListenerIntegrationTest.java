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
/**
 * 
 */
package org.mnode.base.xmpp.integration;

import java.io.IOException;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.mnode.base.config.UnsupportedValueConversionException;

/**
 * @author fortuna
 * 
 */
public class RosterListenerIntegrationTest extends AbstractXmppIntegrationTest {

    private static final Log LOG = LogFactory.getLog(RosterListenerIntegrationTest.class);

    public void testRegisterRosterListener() throws XMPPException, IOException, UnsupportedValueConversionException {

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

        XMPPConnection connection = newConnection();
        connection.disconnect();
    }

}
