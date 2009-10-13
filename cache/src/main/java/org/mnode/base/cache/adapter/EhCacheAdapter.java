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

package org.mnode.base.cache.adapter;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.mnode.base.cache.CacheAdapter;
import org.mnode.base.cache.CacheEntry;

/**
 * @author fortuna
 *
 */
public class EhCacheAdapter implements CacheAdapter {

    private final Cache cache;
    
    public EhCacheAdapter(Cache cache) {
        this.cache = cache;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T get(CacheEntry entry, Object... args) {
        T value = null;
        final String key = entry.getKey(args);
        Element cacheElement = cache.get(key);
        if (cacheElement != null) {
            value = (T) cacheElement.getValue();
        }
        else {
            value = (T) entry.load(args);
            cache.put(new Element(key, value));
        }
        return value;
    }

}
