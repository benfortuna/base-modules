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
package org.mnode.base.views.tracker;

import java.awt.event.ComponentEvent;

import org.jdesktop.swingx.JXStatusBar;

/**
 * @author Ben
 * 
 */
public class JXStatusBarTracker extends ComponentTracker {

    JXStatusBar statusBar;

    /**
     * @param statusBar the status bar to track
     */
    public JXStatusBarTracker(JXStatusBar statusBar) {
        super(statusBar);
    }

    /**
     * @param statusBar the status bar to track
     * @param id an identifier for this tracker
     */
    public JXStatusBarTracker(JXStatusBar statusBar, String id) {
        super(statusBar, id);
        this.statusBar = statusBar;
        statusBar.setVisible(isVisible());
    }

    /**
     * {@inheritDoc}
     */
    public void componentHidden(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", false);
    }

    /**
     * {@inheritDoc}
     */
    public void componentShown(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", true);
    }

    /**
     * @return the saved visibility of the status bar.
     */
    public boolean isVisible() {
        return getPreferences().getBoolean(getUniqueId() + ".visible", statusBar.isVisible());
    }

}
