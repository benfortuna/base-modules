/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2010, Ben Fortuna [fortuna@micronode.com]
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

package org.mnode.base.desktop;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * @author Ben
 *
 */
public class PaddedIcon implements Icon {

    private final Icon icon;
    
    private final int width;
    
    private final int height;
    
    public PaddedIcon(Icon icon, int width, int height) {
        this.icon = icon;
        this.width = Math.max(width, icon.getIconWidth());
        this.height = Math.max(height, icon.getIconHeight());
    }
    
    /**
     * {@inheritDoc}
     */
    public int getIconHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    public int getIconWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        icon.paintIcon(c, g, (int) (x + (width - icon.getIconWidth()) / 2),
                (int) (y + (height - icon.getIconHeight()) / 2));
    }

}
