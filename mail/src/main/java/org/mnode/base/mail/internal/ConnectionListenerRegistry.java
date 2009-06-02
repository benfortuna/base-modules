/**
 * 
 */
package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Service;
import javax.mail.event.ConnectionListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for service connection events.
 * @author fortuna
 */
public class ConnectionListenerRegistry extends
        AbstractPubSubRegistry<Service, ConnectionListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Service publisher, ConnectionListener subscriber,
            Map<String, ?> properties) {
        publisher.addConnectionListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Service publisher,
            ConnectionListener subscriber, Map<String, ?> properties) {
        publisher.removeConnectionListener(subscriber);
    }
}
