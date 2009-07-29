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

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author fortuna
 *
 */
public class ConfigurationPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationPlaceholderConfigurer.class);
    
    private final Configuration configuration;
    
    /**
     * 
     */
    public ConfigurationPlaceholderConfigurer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        String value = null;
        try {
            value = configuration.get(new StringPropertyName(placeholder));
        } catch (UnsupportedValueConversionException e) {
            LOGGER.error("Error in property conversion", e);
            value = super.resolvePlaceholder(placeholder, props);
        }
        return value;
    }
    
    private class StringPropertyName implements PropertyName {
        
        private final String key;
        
        public StringPropertyName(String key) {
            this.key = key;
        }
        
        @Override
        public String getKey() {
            return key;
        }
        @Override
        public <T> Class<T> getType() {
            return (Class<T>) String.class;
        }
    }
}
