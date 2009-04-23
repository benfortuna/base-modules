package org.mnode.base.xmpp.internal;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.mnode.base.xmpp.ChatContext;
import org.mnode.base.xmpp.ChatContextManager;

public class ChatContextManagerImpl implements ChatContextManager, ConnectionCreationListener {

	private Map<Chat, ChatContext> contexts;
	
	public ChatContextManagerImpl() {
		contexts = new HashMap<Chat, ChatContext>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public ChatContext getContext(Chat chat) {
		return contexts.get(chat);
	}

	@Override
	public void connectionCreated(final XMPPConnection connection) {
		connection.getChatManager().addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean createdLocally) {
				contexts.put(chat, new ChatContext(connection));
			}
		});
	}
}
