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

package org.mnode.base.substance;

import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import org.jvnet.substance.api.SubstanceConstants.TabCloseKind;
import org.jvnet.substance.api.tabbed.TabCloseCallback;

/**
 * @author Ben
 *
 */
public class TabCloseCallbackImpl implements TabCloseCallback {

    /**
     * {@inheritDoc}
     */
    public String getAreaTooltip(JTabbedPane arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getCloseButtonTooltip(JTabbedPane tabbedPane, int tabIndex) {
        StringBuffer result = new StringBuffer();
        result.append("<html><body>");
        result.append("Mouse click closes <b>" + tabbedPane.getTitleAt(tabIndex) + "</b> tab");
        result.append("<br><b>Alt</b>-Mouse click closes all tabs but <b>" + tabbedPane.getTitleAt(tabIndex) + "</b> tab");
        result.append("<br><b>Shift</b>-Mouse click closes all tabs");
        result.append("</body></html>");
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public TabCloseKind onAreaClick(JTabbedPane arg0, int arg1, MouseEvent mouseEvent) {
        if (mouseEvent.getButton() != MouseEvent.BUTTON2) {
            return TabCloseKind.NONE;
        }
        if (mouseEvent.isShiftDown()) {
            return TabCloseKind.ALL;
        }
        return TabCloseKind.THIS;
    }

    /**
     * {@inheritDoc}
     */
    public TabCloseKind onCloseButtonClick(JTabbedPane arg0, int arg1, MouseEvent mouseEvent) {
        if (mouseEvent.isAltDown()) {
            return TabCloseKind.ALL_BUT_THIS;
        }
        if (mouseEvent.isShiftDown()) {
            return TabCloseKind.ALL;
        }
        return TabCloseKind.THIS;
    }

}
