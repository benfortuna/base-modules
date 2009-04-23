package org.mnode.base.xmpp.integration;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.mnode.base.xmpp.ChatContext;
import org.mnode.base.xmpp.ChatContextManager;
import org.osgi.util.tracker.ServiceTracker;


public class ChatContextManagerImplTest extends AbstractXmppTest {
	
	public void testGetChatContext() throws XMPPException {

		ServiceTracker tracker = new ServiceTracker(bundleContext, ChatContextManager.class.getName(), null);
		tracker.open();
		final ChatContextManager contextManager = (ChatContextManager) tracker.getService();
		assertNotNull(contextManager);
		
		final XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
		
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
