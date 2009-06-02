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
package org.mnode.base.xmpp.internal;

import java.util.Map;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for file transfer events.
 * 
 * @author fortuna
 *
 */
public class FileTransferListenerRegistry extends
        AbstractPubSubRegistry<FileTransferManager, FileTransferListener>
        implements ConnectionCreationListener {

    /**
     * 
     */
    public FileTransferListenerRegistry() {
        XMPPConnection.addConnectionCreationListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connectionCreated(XMPPConnection connection) {
        FileTransferManager ftm = new FileTransferManager(connection);
        registerPublisher(ftm, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(FileTransferManager publisher,
            FileTransferListener subscriber, Map<String, ?> properties) {
        publisher.addFileTransferListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(FileTransferManager publisher,
            FileTransferListener subscriber, Map<String, ?> properties) {
        publisher.removeFileTransferListener(subscriber);
    }
}
