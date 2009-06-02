package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Store;
import javax.mail.event.FolderListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for store folder events.
 * 
 * @author fortuna
 */
public class FolderListenerRegistry extends
        AbstractPubSubRegistry<Store, FolderListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Store publisher, FolderListener subscriber,
            Map<String, ?> properties) {
        publisher.addFolderListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Store publisher, FolderListener subscriber,
            Map<String, ?> properties) {
        publisher.removeFolderListener(subscriber);
    }
}
