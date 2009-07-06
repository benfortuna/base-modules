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
 * @author fortuna
 *
 */
public abstract class AbstractConfiguration implements Configuration {
    
    private final PropertyValueConverterRegistry converterRegistry;

    public AbstractConfiguration() {
        this(new PropertyValueConverterRegistry());
    }
    
    public AbstractConfiguration(PropertyValueConverterRegistry registry) {
        this.converterRegistry = registry;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <T> T get(PropertyName name) throws UnsupportedValueConversionException {
        T value = (T) get(name, null);
        return value;
    }

    protected final <T> T convert(String value, Class<? extends T> returnType) throws UnsupportedValueConversionException {
        PropertyValueConverter<T> converter = converterRegistry.getConverter(returnType);
        if (converter != null) {
            return converter.convert(value);
        }
        else {
            throw new UnsupportedValueConversionException("Unsupported return type [" + returnType + "]");
        }
    }

}
