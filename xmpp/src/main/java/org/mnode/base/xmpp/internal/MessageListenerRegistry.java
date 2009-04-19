package org.mnode.base.xmpp.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;

public class MessageListenerRegistry implements ConnectionCreationListener, ChatManagerListener {

	private List<Chat> chats;
	
	private List<MessageListener> messageListeners;
	
	public MessageListenerRegistry() {
		this.chats = new ArrayList<Chat>();
		this.messageListeners = new ArrayList<MessageListener>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
		connection.getChatManager().addChatListener(this);
	}
	
	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		chats.add(chat);
		for (MessageListener listener : messageListeners) {
			chat.addMessageListener(listener);
		}
	}
	
	public void addMessageListener(MessageListener listener, Map<?, ?> props) {
		messageListeners.add(listener);
		for (Chat chat : chats) {
			chat.addMessageListener(listener);
		}
	}
	
	public void removeMessageListener(MessageListener listener, Map<?, ?> props) {
		messageListeners.remove(listener);
		for (Chat chat : chats) {
			chat.removeMessageListener(listener);
		}
	}
}
