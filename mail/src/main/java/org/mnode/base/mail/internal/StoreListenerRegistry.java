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
package org.mnode.base.mail.internal;

import java.util.Map;

import javax.mail.Store;
import javax.mail.event.StoreListener;

import org.mnode.base.commons.AbstractPubSubRegistry;

/**
 * A pub/sub registry for store events.
 * 
 * @author fortuna
 */
public class StoreListenerRegistry extends
        AbstractPubSubRegistry<Store, StoreListener> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void subscribe(Store store, StoreListener subscriber,
            Map<String, ?> props) {
        store.addStoreListener(subscriber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unsubscribe(Store store, StoreListener subscriber,
            Map<String, ?> props) {
        store.removeStoreListener(subscriber);
    }
}
