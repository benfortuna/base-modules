/**
 * 
 */
package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Folder;
import javax.mail.event.ConnectionListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for folder connection events.
 * @author fortuna
 */
public class FolderConnectionListenerRegistry extends
        AbstractPubSubRegistry<Folder, ConnectionListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Folder publisher, ConnectionListener subscriber,
            Map<String, ?> properties) {
        publisher.addConnectionListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Folder publisher, ConnectionListener subscriber,
            Map<String, ?> properties) {
        publisher.removeConnectionListener(subscriber);
    }
}
