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

import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;

/**
 * Support for generating cross-process unique identifiers.
 * 
 * @author fortuna
 *
 */
public class IdGenerator {

    private final String prefix;
    
    private final AtomicInteger count;
    
    private final String pid;
    
    /**
     * @param prefix an optional prefix for generated identifiers
     */
    public IdGenerator(String prefix) {
        this.prefix = prefix;
        this.count = new AtomicInteger();
        this.pid = ManagementFactory.getRuntimeMXBean().getName();
    }
    
    /**
     * @return an identifier guaranteed to be unique across processes
     */
    public String generateId() {
        final StringBuilder b = new StringBuilder();
        if (StringUtils.isNotEmpty(prefix)) {
            b.append(prefix).append('-');
        }
        b.append(count.incrementAndGet()).append(':').append(pid);
        return b.toString();
    }
}
