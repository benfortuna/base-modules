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

import java.util.HashMap;
import java.util.Map;

import org.mnode.base.config.converter.IntegerValueConverter;
import org.mnode.base.config.converter.StringValueConverter;

/**
 * @author fortuna
 *
 */
public class PropertyValueConverterRegistry {
    
    private final Map<Class<?>, PropertyValueConverter<?>> defaultConverters;
    
    private final Map<Class<?>, PropertyValueConverter<?>> extendedConverters;

    public PropertyValueConverterRegistry() {
        defaultConverters = new HashMap<Class<?>, PropertyValueConverter<?>>();
        defaultConverters.put(Integer.class, new IntegerValueConverter());
        defaultConverters.put(String.class, new StringValueConverter());
        extendedConverters = new HashMap<Class<?>, PropertyValueConverter<?>>();
    }
    
    /**
     * @param <T>
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> PropertyValueConverter<T> getConverter(Class<? extends T> type) {
        PropertyValueConverter<T> converter = (PropertyValueConverter<T>) extendedConverters.get(type);
        if (converter == null) {
            converter = (PropertyValueConverter<T>) defaultConverters.get(type);
        }
        return converter;
    }
    
    /**
     * @param <T>
     * @param type
     * @param converter
     */
    public <T> void register(Class<T> type, PropertyValueConverter<T> converter) {
        extendedConverters.put(type, converter);
    }
}
