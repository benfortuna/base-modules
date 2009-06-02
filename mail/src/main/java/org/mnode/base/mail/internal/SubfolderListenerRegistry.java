package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Folder;
import javax.mail.event.FolderListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for folder events.
 * 
 * @author fortuna
 */
public class SubfolderListenerRegistry extends
        AbstractPubSubRegistry<Folder, FolderListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Folder publisher, FolderListener subscriber,
            Map<String, ?> properties) {
        publisher.addFolderListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Folder publisher, FolderListener subscriber,
            Map<String, ?> properties) {
        publisher.removeFolderListener(subscriber);
    }
}
