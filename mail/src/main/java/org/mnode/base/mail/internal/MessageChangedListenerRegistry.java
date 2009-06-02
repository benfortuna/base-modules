package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Folder;
import javax.mail.event.MessageChangedListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for folder message change events.
 * 
 * @author fortuna
 */
public class MessageChangedListenerRegistry extends
        AbstractPubSubRegistry<Folder, MessageChangedListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Folder publisher,
            MessageChangedListener subscriber, Map<String, ?> properties) {
        publisher.addMessageChangedListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Folder publisher,
            MessageChangedListener subscriber, Map<String, ?> properties) {
        publisher.removeMessageChangedListener(subscriber);
    }
}
