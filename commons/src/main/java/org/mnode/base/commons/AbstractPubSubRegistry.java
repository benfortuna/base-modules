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
package org.mnode.base.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for registry implementations to assist implementation of the
 * whiteboard pattern.
 * 
 * @author fortuna
 * 
 * @param <P>
 *            the publisher type
 * @param <S>
 *            the subscriber type
 */
public abstract class AbstractPubSubRegistry<P, S> {

    private List<P> publishers;

    private Map<S, Map<String, ?>> subscribers;

    /**
     * 
     */
    public AbstractPubSubRegistry() {
        publishers = new ArrayList<P>();
        subscribers = new HashMap<S, Map<String, ?>>();
    }

    /**
     * @param publisher the source
     * @param properties additional publishing properties
     */
    public final void registerPublisher(P publisher, Map<String, ?> properties) {
        publishers.add(publisher);
        for (S subscriber : subscribers.keySet()) {
            subscribe(publisher, subscriber, subscribers.get(subscriber));
        }
    }

    /**
     * @param publisher the source
     * @param properties additional publishing properties
     */
    public final void unregisterPublisher(P publisher, Map<String, ?> properties) {
        publishers.remove(publisher);
        for (S subscriber : subscribers.keySet()) {
            unsubscribe(publisher, subscriber, subscribers.get(subscriber));
        }
    }

    /**
     * @param subscriber the destination
     * @param properties additional subscription properties
     */
    public final void registerSubscriber(S subscriber, Map<String, ?> properties) {
        subscribers.put(subscriber, properties);
        for (P publisher : publishers) {
            subscribe(publisher, subscriber, properties);
        }
    }

    /**
     * @param subscriber the destination
     * @param properties additional subscription properties
     */
    public final void unregisterSubscriber(S subscriber,
            Map<String, ?> properties) {
        subscribers.remove(subscriber);
        for (P publisher : publishers) {
            unsubscribe(publisher, subscriber, properties);
        }
    }

    /**
     * @param publisher the source
     * @param subscriber the destination
     * @param properties additional subscription properties
     */
    protected abstract void subscribe(P publisher, S subscriber,
            Map<String, ?> properties);

    /**
     * @param publisher the source
     * @param subscriber the destination
     * @param properties additional subscription properties
     */
    protected abstract void unsubscribe(P publisher, S subscriber,
            Map<String, ?> properties);
}
