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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractPubSubRegistryTest {

    private AbstractPubSubRegistry<String, String> registry;

    private Map<String, String> subscribed;

    @Before
    public void runBeforeEachTest() {
        subscribed = new HashMap<String, String>();

        registry = new AbstractPubSubRegistry<String, String>() {
            @Override
            protected void subscribe(String publisher, String subscriber,
                    Map<String, ?> props) {
                subscribed.put(publisher, subscriber);
            }

            @Override
            protected void unsubscribe(String publisher, String subscriber,
                    Map<String, ?> props) {
                subscribed.remove(publisher);
            }
        };
    }

    @Test
    public void testSubscribeAfterPublisher() {
        String publisher = "pub";
        String subscriber = "sub";
        Map<String, String> props = new HashMap<String, String>();
        registry.registerPublisher(publisher, props);
        registry.registerSubscriber(subscriber, props);

        Assert.assertEquals(subscriber, subscribed.get(publisher));
    }

    @Test
    public void testSubscribeBeforePublisher() {
        String publisher = "pub";
        String subscriber = "sub";
        Map<String, String> props = new HashMap<String, String>();
        registry.registerSubscriber(subscriber, props);
        registry.registerPublisher(publisher, props);

        Assert.assertEquals(subscriber, subscribed.get(publisher));
    }

    @Test
    public void testUnsubscribeAfterPublisher() {
        String publisher = "pub";
        String subscriber = "sub";
        Map<String, String> props = new HashMap<String, String>();
        registry.registerPublisher(publisher, props);
        registry.registerSubscriber(subscriber, props);

        Assert.assertEquals(subscriber, subscribed.get(publisher));
        registry.unregisterPublisher(publisher, props);
        registry.unregisterSubscriber(subscriber, props);
        Assert.assertNull(subscribed.get(publisher));
    }

    @Test
    public void testUnsubscribeBeforePublisher() {
        String publisher = "pub";
        String subscriber = "sub";
        Map<String, String> props = new HashMap<String, String>();
        registry.registerSubscriber(subscriber, props);
        registry.registerPublisher(publisher, props);

        Assert.assertEquals(subscriber, subscribed.get(publisher));
        registry.unregisterSubscriber(subscriber, props);
        registry.unregisterPublisher(publisher, props);

        Assert.assertNull(subscribed.get(publisher));
    }
    
    @Test
    public void testUnregisterAll() {
        String publisher = "pub";
        String subscriber = "sub";
        Map<String, String> props = new HashMap<String, String>();
        registry.registerPublisher(publisher, props);
        registry.registerSubscriber(subscriber, props);
        Assert.assertEquals(subscriber, subscribed.get(publisher));

        registry.unregisterAll();
        Assert.assertNull(subscribed.get(publisher));
    }
}
