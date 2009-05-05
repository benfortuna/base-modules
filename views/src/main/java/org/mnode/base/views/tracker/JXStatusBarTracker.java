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
	 * @param component
	 */
	public JXStatusBarTracker(JXStatusBar statusBar) {
		super(statusBar);
	}

	/**
	 * @param component
	 * @param id
	 */
	public JXStatusBarTracker(JXStatusBar statusBar, String id) {
		super(statusBar, id);
		this.statusBar = statusBar;
		statusBar.setVisible(isVisible());
	}

    /**
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", false);
    }

    /**
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(final ComponentEvent e) {
        getPreferences().putBoolean(getUniqueId() + ".visible", true);
    }

    /**
     * Returns the saved visibility of the status bar.
     * @return
     */
    public boolean isVisible() {
        return getPreferences().getBoolean(getUniqueId() + ".visible",
                statusBar.isVisible());
    }

}
