package org.mnode.base.xmpp;

import org.jivesoftware.smack.XMPPConnection;

public class ChatContext {

	private XMPPConnection connection;
	
	public ChatContext(XMPPConnection connection) {
		this.connection = connection;
	}

	/**
	 * @return the connection
	 */
	public XMPPConnection getConnection() {
		return connection;
	}
}
