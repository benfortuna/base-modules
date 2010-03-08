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
package org.mnode.base.desktop.tracker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSplitPane;

import org.apache.commons.logging.LogFactory;
import org.mnode.base.log.FormattedLogEntry;
import org.mnode.base.log.LogAdapter;
import org.mnode.base.log.LogEntry;
import org.mnode.base.log.LogEntry.Level;
import org.mnode.base.log.adapter.JclAdapter;

/**
 * Preferences for JSplitPanes
 * 
 * @author Ben Fortuna
 */
public class JSplitPaneTracker extends ComponentTracker implements PropertyChangeListener {

    private static final LogAdapter LOG = new JclAdapter(LogFactory.getLog(JSplitPaneTracker.class));

    private static final LogEntry PROPERTY_CHANGED_LOG = new FormattedLogEntry(Level.Debug, "Property changed: %s");
    
    private JSplitPane pane;

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param pane the split pane to track
     */
    protected JSplitPaneTracker(JSplitPane pane) {
        this(pane, null);
    }

    /**
     * Constructor made protected to ensure only instantiated by PrefsManager.
     * 
     * @param pane the split plane to track
     * @param id an identifier for this tracker
     */
    protected JSplitPaneTracker(JSplitPane pane, String id) {
        super(pane, id);

        this.pane = pane;
        pane.setDividerLocation(getDividerLocation());
        pane.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public void propertyChange(PropertyChangeEvent e) {

        LOG.log(PROPERTY_CHANGED_LOG, e.getPropertyName());

        if ("dividerLocation".equals(e.getPropertyName())) {
            getPreferences().putInt(getUniqueId() + "." + e.getPropertyName(), pane.getDividerLocation());
        }
    }

    /**
     * @return the last known divider location for the split pane
     */
    public int getDividerLocation() {
        return getPreferences().getInt(getUniqueId() + ".dividerLocation", pane.getDividerLocation());
    }
}
