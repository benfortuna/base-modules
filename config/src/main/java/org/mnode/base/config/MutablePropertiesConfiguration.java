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

/**
 * @author fortuna
 *
 */
public class MutablePropertiesConfiguration extends PropertiesConfiguration implements MutableConfiguration {

    private Properties properties;
    
    /**
     * @param properties
     */
    public MutablePropertiesConfiguration(Properties properties) {
        super(properties);
        this.properties = properties;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void add(PropertyName name, T value) {
        throw new UnsupportedOperationException("Multi-value properties not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        properties.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(PropertyName name) {
        properties.remove(name.getKey());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void set(PropertyName name, T value) {
        if (value != null) {
            
        }
        else {
            // where value is null unset property..
            remove(name);
        }
    }
    
    /**
     * @return the underlying properties for the configuration
     */
    public Properties getProperties() {
        return properties;
    }
}
