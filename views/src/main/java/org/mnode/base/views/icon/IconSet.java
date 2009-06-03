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

import javax.swing.Icon;

/**
 * Defines access to a set of icons.
 * @author benfortuna
 */
public interface IconSet {

    /**
     * @return the identifier of the icon set.
     */
    String getId();
    
    /**
     * @return the name of the icon set.
     */
    String getName();
    
    /**
     * @return a description of the icon set.
     */
    String getDescription();
    
    /**
     * @return the author of the icon set.
     */
    String getAuthor();
    
    /**
     * Returns a default icon from the icon set associated with the specified key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getIcon(final String key);

    /**
     * Returns a rollover icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getRolloverIcon(final String key);

    /**
     * Returns a disabled icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getDisabledIcon(final String key);

    /**
     * Returns a pressed icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getPressedIcon(final String key);

    /**
     * Returns a selected icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getSelectedIcon(final String key);

    /**
     * Indicates whether pressed icons are generated where a pressed icon is not
     * found.
     * @return Returns the pressedIconGenerated.
     */
    boolean isPressedIconGenerated();
}
