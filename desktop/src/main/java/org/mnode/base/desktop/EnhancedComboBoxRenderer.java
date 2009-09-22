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
package org.mnode.base.desktop;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 * Enhance the default combo box renderer by adding support for separators.
 * 
 * @author Ben Fortuna
 */
public class EnhancedComboBoxRenderer extends BasicComboBoxRenderer {

    private static final long serialVersionUID = 1768975539253158314L;

    /**
     * {@inheritDoc}
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        final Component defaultRenderer = super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);
        if (value instanceof JSeparator) {
            return (Component) value;
        }
        return defaultRenderer;
    }
}
