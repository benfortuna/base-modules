package org.mnode.base.xmpp.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;

public class FileTransferListenerTest extends AbstractXmppTest {

	private static final Log LOG = LogFactory.getLog(FileTransferListenerTest.class);

	public void testRegisterFileTransferListener() throws XMPPException {
		FileTransferListener listener = new FileTransferListener() {
			@Override
			public void fileTransferRequest(FileTransferRequest request) {
				LOG.info("File transfer requested: " + request.getFileName() + " (" + request.getFileSize() + " bytes)");
			}
		};
		
		bundleContext.registerService(FileTransferListener.class.getName(), listener, null);

		XMPPConnection connection = new XMPPConnection("basepatterns.org");
		connection.connect();
		connection.login("test", "!password");
		
		connection.disconnect();
	}

}
