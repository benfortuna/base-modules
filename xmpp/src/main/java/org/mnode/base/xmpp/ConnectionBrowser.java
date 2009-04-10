/**
 * 
 */
package org.mnode.base.xmpp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

/**
 * @author fortuna
 *
 */
public class ConnectionBrowser implements ConnectionCreationListener, ConnectionListener {

	private List<XMPPConnection> connections;
	
	public ConnectionBrowser() {
		connections = new ArrayList<XMPPConnection>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	/**
	 * @return an immutable list of current connections
	 */
	public List<XMPPConnection> getConnections() {
		return Collections.unmodifiableList(connections);
	}

	@Override
	public void connectionCreated(XMPPConnection connection) {
		connection.addConnectionListener(this);
		connections.add(connection);
	}
	
	@Override
	public void connectionClosed() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void connectionClosedOnError(Exception e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reconnectingIn(int seconds) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reconnectionFailed(Exception e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reconnectionSuccessful() {
		// TODO Auto-generated method stub
		
	}
}
