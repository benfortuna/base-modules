package org.mnode.base.xmpp.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.ChatStateManager;
import org.jivesoftware.smackx.ServiceDiscoveryManager;

public class ChatManagerListenerRegistry implements ConnectionCreationListener {

	static {
		// XXX: hack to load service discovery manager and correctly register itself
		// as a connection creation listener..
		ServiceDiscoveryManager.getIdentityName();
	}
	
	private List<XMPPConnection> connections;
	
	private List<ChatManagerListener> listeners;
	
	public ChatManagerListenerRegistry() {
		this.connections = new ArrayList<XMPPConnection>();
		this.listeners = new ArrayList<ChatManagerListener>();
		XMPPConnection.addConnectionCreationListener(this);
	}

	@Override
	public void connectionCreated(XMPPConnection connection) {
		connections.add(connection);
        
        //NOTE: getInstance(org.jivesoftware.smack.XMPPConnection)  needs to be 
        // called in order for the listeners to be registered appropriately with 
        // the connection. If this does not occur you will not receive the update notifications.
        ChatStateManager.getInstance(connection);
        
		for (ChatManagerListener listener : listeners) {
			connection.getChatManager().addChatListener(listener);
		}
	}

	/*
	public void addConnection(XMPPConnection connection, Map<?, ?> props) {
		connections.add(connection);
		for (ChatManagerListener listener : listeners) {
			connection.getChatManager().addChatListener(listener);
		}
	}
	
	public void removeConnection(XMPPConnection connection, Map<?, ?> props) {
		connections.remove(connection);
		for (ChatManagerListener listener : listeners) {
			connection.getChatManager().removeChatListener(listener);
		}
	}
	*/
	
	public void addChatManagerListener(ChatManagerListener listener, Map<?, ?> props) {
		listeners.add(listener);
		for (XMPPConnection connection : connections) {
			connection.getChatManager().addChatListener(listener);
		}
	}
	
	public void removeChatManagerListener(ChatManagerListener listener, Map<?, ?> props) {
		listeners.remove(listener);
		for (XMPPConnection connection : connections) {
			connection.getChatManager().removeChatListener(listener);
		}
	}
}
