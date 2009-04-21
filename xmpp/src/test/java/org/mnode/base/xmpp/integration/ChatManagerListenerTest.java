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
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ChatManagerListenerTest extends AbstractConfigurableBundleCreatorTests {

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
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT",
			"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
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
