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

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mnode.base.cache.CacheEntry;

import net.sf.ehcache.CacheManager;


/**
 * @author fortuna
 *
 */
public class EhCacheAdapterTest {

    private EhCacheAdapter cache;
    
    private CacheEntry entry;
    
    private final String cachedObject = "test";
    
    @Before
    public void runBeforeEachTest() {
        
        CacheManager cacheManager = CacheManager.create();
        cacheManager.addCache("test");
        cache = new EhCacheAdapter(cacheManager.getCache("test"));
        
        entry = new CacheEntry() {
            
            public Object load(Object... args) {
                return cachedObject;
            }
            
            public String getKey(Object... args) {
                return String.format("cachedObject.%s", args);
            }
        };
    }
    
    @Test
    public void testGet() {
        Assert.assertEquals(cachedObject, cache.get(entry, "test"));
    }
}
