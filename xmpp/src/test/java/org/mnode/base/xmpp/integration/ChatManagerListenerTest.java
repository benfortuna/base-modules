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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.ChatState;
import org.jivesoftware.smackx.ChatStateListener;

/**
 * @author fortuna
 *
 */
public class ChatManagerListenerTest extends AbstractXmppTest {

	private static final Log LOG = LogFactory.getLog(ChatManagerListenerTest.class);
	
	public void testRegisterChatManagerListener() throws XMPPException {
		
		final MessageListener messageListener = new MessageListenerImpl();
		
		ChatManagerListener listener = new ChatManagerListener() {
			@Override
			public void chatCreated(Chat arg0, boolean arg1) {
				LOG.info("Chat created: " + arg0.getParticipant());
				
				arg0.addMessageListener(messageListener);
			}
		};
		bundleContext.registerService(ChatManagerListener.class.getName(), listener, null);

		XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
//		bundleContext.registerService(XMPPConnection.class.getName(), connection, null);
		
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
				}
				else {
					LOG.info("Packet received: " + arg1.getType());
				}
			}
			catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void stateChanged(Chat arg0, ChatState arg1) {
            if (ChatState.composing.equals(arg1)) {
            	LOG.info(arg0.getParticipant() + " is typing..");
            }
            else if (ChatState.gone.equals(arg1)) {
            	LOG.info(arg0.getParticipant() + " has left the conversation.");
            }
            else {
            	LOG.info(arg0.getParticipant() + ": " + arg1);
            }
		}
	}

}
