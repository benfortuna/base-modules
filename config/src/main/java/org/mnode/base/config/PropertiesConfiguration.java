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
    @Override
    public <T> List<T> getAll(PropertyName name) throws UnsupportedValueConversionException {
        List<T> values = new ArrayList<T>();
        T value = get(name);
        if (value != null) {
            values.add(value);
        }
        else {
            for (int i = 0;; i++) {
                value = getMultiValue(name, i);
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
