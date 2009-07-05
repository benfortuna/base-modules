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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author fortuna
 *
 */
public class PropertiesConfigurationTest {

    private PropertiesConfiguration configuration;

    private PropertyName stringProperty;
    
    private PropertyName integerProperty;
    
    private String propertyKey = "test";
    
    private String propertyValue = "1";
    
    @Before
    public void runBeforeEachTest() {
        Properties properties = new Properties();
        properties.setProperty(propertyKey, propertyValue);
        configuration = new PropertiesConfiguration(properties);
        
        stringProperty = new PropertyName() {
            
            @SuppressWarnings("unchecked")
            @Override
            public <T> Class<T> getType() {
                return (Class<T>) String.class;
            }
            
            @Override
            public String getKey() {
                return propertyKey;
            }
        };
        
        integerProperty = new PropertyName() {
            
            @SuppressWarnings("unchecked")
            @Override
            public <T> Class<T> getType() {
                return (Class<T>) Integer.class;
            }
            
            @Override
            public String getKey() {
                return propertyKey;
            }
        };
    }
    
    @Test
    public void testGetStringProperty() throws UnsupportedValueConversionException {
        String property = configuration.get(stringProperty, null);
        Assert.assertNotNull(property);
        Assert.assertEquals(propertyValue, property);
    }
    
    @Test
    public void testGetIntegerProperty() throws UnsupportedValueConversionException {
        Integer property = configuration.get(integerProperty, null);
        Assert.assertNotNull(property);
        Assert.assertEquals(Integer.valueOf(propertyValue), property);
    }
}
