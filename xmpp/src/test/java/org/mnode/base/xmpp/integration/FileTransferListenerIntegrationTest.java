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
package org.mnode.base.xmpp.integration;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.mnode.base.config.UnsupportedValueConversionException;

public class FileTransferListenerIntegrationTest extends AbstractXmppIntegrationTest {

    private static final Log LOG = LogFactory
            .getLog(FileTransferListenerIntegrationTest.class);

    public void testRegisterFileTransferListener() throws XMPPException, IOException, UnsupportedValueConversionException {
        FileTransferListener listener = new FileTransferListener() {
            @Override
            public void fileTransferRequest(FileTransferRequest request) {
                LOG.info("File transfer requested: " + request.getFileName()
                        + " (" + request.getFileSize() + " bytes)");
            }
        };

        bundleContext.registerService(FileTransferListener.class.getName(),
                listener, null);

        XMPPConnection connection = newConnection();

        connection.disconnect();
    }

}
