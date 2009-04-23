/*
 * $Id: JXStatusBarTracker.java $
 *
 * Created: [03/08/2008]
 *
 * Copyright (c) 2008, Ben Fortuna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
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
