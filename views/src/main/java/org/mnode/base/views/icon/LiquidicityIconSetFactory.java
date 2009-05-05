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


/**
 * @author Ben
 *
 */
public class LiquidicityIconSetFactory extends AbstractIconSetFactory {

    private IconSet defaultIconSet;
    
    /**
     * 
     */
    public LiquidicityIconSetFactory() {
        defaultIconSet = new LiquidicityIconSet();
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getAvailableIds()
     */
    @Override
    public String[] getAvailableIds() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getDefaultIconSet()
     */
    @Override
    public IconSet getDefaultIconSet() {
        return defaultIconSet;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getIconSet(java.lang.String)
     */
    @Override
    public IconSet getIconSet(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
