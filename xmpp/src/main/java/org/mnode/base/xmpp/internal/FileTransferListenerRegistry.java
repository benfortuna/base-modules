package org.mnode.base.xmpp.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;

public class FileTransferListenerRegistry implements ConnectionCreationListener {
	
	private List<FileTransferManager> fileTransferManagers;
	
	private List<FileTransferListener> listeners;
	
	public FileTransferListenerRegistry() {
		this.fileTransferManagers = new ArrayList<FileTransferManager>();
		this.listeners = new ArrayList<FileTransferListener>();
		XMPPConnection.addConnectionCreationListener(this);
	}
	
	@Override
	public void connectionCreated(XMPPConnection connection) {
		FileTransferManager ftm = new FileTransferManager(connection);
		fileTransferManagers.add(ftm);
		
		for (FileTransferListener listener : listeners) {
			ftm.addFileTransferListener(listener);
		}
	}

	public void addFileTransferListener(FileTransferListener listener, Map<?, ?> props) {
		listeners.add(listener);
		for (FileTransferManager ftm : fileTransferManagers) {
			ftm.addFileTransferListener(listener);
		}
	}

	public void removeFileTransferListener(FileTransferListener listener, Map<?, ?> props) {
		listeners.remove(listener);
		for (FileTransferManager ftm : fileTransferManagers) {
			ftm.removeFileTransferListener(listener);
		}
	}

}
