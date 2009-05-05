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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSplitPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Preferences for JSplitPanes
 *
 * @author Ben Fortuna
 */
public class JSplitPaneTracker extends ComponentTracker
		implements PropertyChangeListener {

    private static final Log LOG = LogFactory.getLog(JSplitPaneTracker.class);

    private JSplitPane pane;

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param pane
     */
    protected JSplitPaneTracker(JSplitPane pane) {
        this(pane, null);
    }

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param pane
     * @param id
     */
    protected JSplitPaneTracker(JSplitPane pane, String id) {
        super(pane, id);

        this.pane = pane;
        pane.setDividerLocation(getDividerLocation());
        pane.addPropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent e) {

        LOG.debug("Property changed: " + e.getPropertyName());

        if ("dividerLocation".equals(e.getPropertyName())) {
            getPreferences().putInt(getUniqueId() + "." + e.getPropertyName(),
            		pane.getDividerLocation());
        }
    }

    /**
     * @return
     */
    public int getDividerLocation() {
        return getPreferences().getInt(getUniqueId() + ".dividerLocation",
                pane.getDividerLocation());
    }
}
