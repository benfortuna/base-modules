package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Folder;
import javax.mail.event.MessageCountListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for folder message count events.
 * 
 * @author fortuna
 */
public class MessageCountListenerRegistry extends
        AbstractPubSubRegistry<Folder, MessageCountListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Folder publisher, MessageCountListener subscriber,
            Map<String, ?> properties) {
        publisher.addMessageCountListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Folder publisher,
            MessageCountListener subscriber, Map<String, ?> properties) {
        publisher.removeMessageCountListener(subscriber);
    }
}
