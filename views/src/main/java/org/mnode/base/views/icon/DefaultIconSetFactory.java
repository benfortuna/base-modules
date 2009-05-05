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
package org.mnode.base.views.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A default implementation of an icon set factory. This implementation
 * looks for icon sets defined by XML-based documents in the classpath.
 * @author Ben Fortuna
 */
public final class DefaultIconSetFactory extends AbstractIconSetFactory {

    private static final String ICONSET_DEFINITION_PATH = "/icons/net/fortuna/toolbag/ui/icon/iconset.xml";

    private static Log log = LogFactory.getLog(DefaultIconSetFactory.class);

    private Map iconSets;

    private IconSet defaultIconSet;

    /**
     * Constructor made private to enforce singleton
     */
    protected DefaultIconSetFactory() {
        iconSets = new HashMap();

        // load available icon sets..
//        try {
//            Enumeration iconSetUrls = getClass().getClassLoader().getResources(ICONSET_DEFINITION_PATH);
            URL url = getClass().getResource(ICONSET_DEFINITION_PATH);

//            while (iconSetUrls.hasMoreElements()) {
            if (url != null) {
//                URL url = (URL) iconSetUrls.nextElement();

                // debugging..
                log.info("Loading icon set [" + url + "]");

                try {
                    IconSet iconSet = new DefaultIconSet(url);

                    iconSets.put(iconSet.getId(), iconSet);

                    // just use the first icon set found as the default..
                    if (defaultIconSet == null) {
                        defaultIconSet = iconSet;
                    }
                }
                catch (Exception e) {
                    log.warn("Error loading icon set [" + url + "]", e);
                }
            }
//        }
//        catch (IOException ioe) {
//            log.error("Error loading icon sets", ioe);
//        }
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getIconSet(java.lang.String)
     */
    public IconSet getIconSet(String id) {
        return (IconSet) iconSets.get(id);
    }

    /**
     * Returns the default icon set. This implementation simply
     * returns the first icon set found in the classpath.
     * @return an icon set, or null if the default icon set
     * is not found
     */
    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getDefaultIconSet()
     */
    public IconSet getDefaultIconSet() {
        return defaultIconSet;
    }


    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getAvailableIds()
     */
    public String[] getAvailableIds() {
        return (String[]) iconSets.keySet().toArray(new String[iconSets.size()]);
    }
}