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
/**
 * 
 */
package org.mnode.base.views.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ben
 *
 */
public class LiquidicityIconSet implements IconSet {

    private static final Log LOG = LogFactory.getLog(LiquidicityIconSet.class);
    
    private Map<String, Icon> icons;
    
    /**
     * 
     */
    public LiquidicityIconSet() {
        icons = new HashMap<String, Icon>();
    }
    
    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getAuthor()
     */
    @Override
    public String getAuthor() {
        return "GoSquared";
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getDescription()
     */
    @Override
    public String getDescription() {
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getDisabledIcon(java.lang.String)
     */
    @Override
    public Icon getDisabledIcon(String key) {
        return getIcon(key + "_disabled");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getIcon(java.lang.String)
     */
    @Override
    public Icon getIcon(String key) {
        Icon icon = icons.get(key);
        if (icon == null) {
            try {
                URL iconUrl = getClass().getResource("/icons/liquidicity/" + key + ".png");
                if (iconUrl != null) {
                    icon = new ImageIcon(iconUrl);
                    icons.put(key, icon);
                }
            }
            catch (Exception e) {
                LOG.warn("Icon [" + key + "] not found.", e);
            }
        }
        return icon;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getId()
     */
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getPressedIcon(java.lang.String)
     */
    @Override
    public Icon getPressedIcon(String key) {
        return getIcon(key + "_pressed");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getRolloverIcon(java.lang.String)
     */
    @Override
    public Icon getRolloverIcon(String key) {
        return getIcon(key + "_rollover");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getSelectedIcon(java.lang.String)
     */
    @Override
    public Icon getSelectedIcon(String key) {
        return getIcon(key + "_selected");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#isPressedIconGenerated()
     */
    @Override
    public boolean isPressedIconGenerated() {
        // TODO Auto-generated method stub
        return false;
    }

}
