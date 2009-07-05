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

/**
 * @param <T> the value type returned
 * 
 * @author fortuna
 *
 */
public interface PropertyValueConverter<T> {

    /**
     * Converts the specified string value into an instance of type T.
     * @param value a string value
     * @return an instance of type T
     * @throws UnsupportedValueConversionException where an instance cannot be created
     * from the specified string
     */
    T convert(String value) throws UnsupportedValueConversionException;
}
