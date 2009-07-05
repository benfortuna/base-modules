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

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.ChatState;
import org.jivesoftware.smackx.ChatStateListener;
import org.mnode.base.config.UnsupportedValueConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fortuna
 * 
 */
public class ChatManagerListenerIntegrationTest extends AbstractXmppIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ChatManagerListenerIntegrationTest.class);

    public void testRegisterChatManagerListener() throws XMPPException, IOException, UnsupportedValueConversionException {

        final MessageListener messageListener = new MessageListenerImpl();

        ChatManagerListener listener = new ChatManagerListener() {
            @Override
            public void chatCreated(Chat arg0, boolean arg1) {
                LOG.info("Chat created: " + arg0.getParticipant());

                arg0.addMessageListener(messageListener);
            }
        };
        bundleContext.registerService(ChatManagerListener.class.getName(), listener, null);

        XMPPConnection connection = newConnection();
        // bundleContext.registerService(XMPPConnection.class.getName(), connection, null);

        connection.disconnect();
    }

    public static class MessageListenerImpl implements ChatStateListener {
        @Override
        public void processMessage(Chat arg0, Message arg1) {
            try {
                if (arg1.getBody() != null) {
                    LOG.info("Message received: " + arg1.getFrom() + "> " + arg1.getBody());

                    try {
                        arg0.sendMessage(arg1.getBody());
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    }
                } else {
                    LOG.info("Packet received: " + arg1.getType());
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void stateChanged(Chat arg0, ChatState arg1) {
            if (ChatState.composing.equals(arg1)) {
                LOG.info(arg0.getParticipant() + " is typing..");
            } else if (ChatState.gone.equals(arg1)) {
                LOG.info(arg0.getParticipant() + " has left the conversation.");
            } else {
                LOG.info(arg0.getParticipant() + ": " + arg1);
            }
        }
    }

}
