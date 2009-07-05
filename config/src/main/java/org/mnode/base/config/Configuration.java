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
public interface Configuration  {
    
    /**
     * Returns a single property value stored under the specified key. If the property is a list
     * the first value in the list is returned. 
     * @param key
     * @return a property value or the specified default if the property does not exist
     * @throws UnsupportedValueConversionException 
     */
    <T> T get(PropertyName name) throws UnsupportedValueConversionException;
    
    /**
     * Returns a single property value stored under the specified key. If the property is a list
     * the first value in the list is returned. 
     * @param key
     * @return a property value or the specified default if the property does not exist
     * @throws UnsupportedValueConversionException 
     */
    <T> T get(PropertyName name, T defaultValue) throws UnsupportedValueConversionException;
    
    /**
     * Returns all configured properties for the specified name.
     * @param <T> the property value type
     * @param name the property name
     * @return a list of configured properties
     * @throws UnsupportedValueConversionException 
     */
    <T> List<T> getAll(PropertyName name) throws UnsupportedValueConversionException;
}
