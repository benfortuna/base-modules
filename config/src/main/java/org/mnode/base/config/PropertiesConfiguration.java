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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertiesConfiguration extends AbstractConfiguration {

    private final Properties properties;
    
    /**
     * @param properties
     */
    public PropertiesConfiguration(Properties properties) {
        this.properties = properties;
    }

    public PropertiesConfiguration(Properties properties, PropertyValueConverterRegistry registry) {
        super(registry);
        this.properties = properties;
    }
    
    @Override
    public <T> T get(PropertyName name, T defaultValue) throws UnsupportedValueConversionException {
        String value = properties.getProperty(name.getKey());
        if (value != null) {
            Class<? extends T> returnType = name.getType();
            return convert(value, returnType);
        }
        return defaultValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getAll(PropertyName name) throws UnsupportedValueConversionException {
        List<T> values = new ArrayList<T>();
        T value = (T) get(name);
        if (value != null) {
            values.add(value);
        }
        else {
            for (int i = 0;; i++) {
                value = (T) getMultiValue(name, i);
                if (value != null) {
                    values.add(value);
                }
                else {
                    break;
                }
            }
        }
        return Collections.unmodifiableList(values);
    }
    
    private <T> T getMultiValue(PropertyName name, int index) throws UnsupportedValueConversionException {
        String value = properties.getProperty(name.getKey() + "." + index);
        if (value != null) {
            Class<? extends T> returnType = name.getType();
            return convert(value, returnType);
        }
        return null;
    }
}
