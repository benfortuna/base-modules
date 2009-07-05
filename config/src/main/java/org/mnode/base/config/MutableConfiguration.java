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

package org.mnode.base.config;

import java.util.List;

/**
 * @author fortuna
 *
 */
public interface MutableConfiguration extends Configuration {

    /**
     * Adds a value to a multi-valued property. Where multi-valued properties are not
     * supported by the configuration an {@link UnsupportedOperationException} is thrown.
     * @param <T> the property value type
     * @param name the property name
     * @param value the property value
     */
    <T> void add(PropertyName name, T value);
    
    /**
     * Removes the specified property from the configuration.
     * @param name a property name
     */
    void remove(PropertyName name);
    
    /**
     * Sets a property value. Where the specified value is null the property is removed
     * from the configuration.
     * @param <T> the property value type
     * @param name a property name
     * @param value the value to associate with the property
     */
    <T> void set(PropertyName name, T value);
    
    /**
     * Remove all configured properties.
     */
    void clear();
}
