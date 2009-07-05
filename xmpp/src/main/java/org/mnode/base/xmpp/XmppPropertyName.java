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

package org.mnode.base.xmpp;

import org.mnode.base.config.PropertyName;

/**
 * @author fortuna
 *
 */
public enum XmppPropertyName implements PropertyName {

    /**
     * The service name (i.e. host) for an XMPP connection.
     */
    ServiceName("connection.serviceName", String.class),
    
    /**
     * The username for an XMPP connection.
     */
    Username("connection.username", String.class),
    
    /**
     * The password for an XMPP connection.
     */
    Password("connection.password", String.class);
    
    private final String key;
    
    private final Class<?> type;
    
    /**
     * @param key
     */
    private XmppPropertyName(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    public String getKey() {
        return key;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> Class<T> getType() {
        return (Class<T>) type;
    }
}
