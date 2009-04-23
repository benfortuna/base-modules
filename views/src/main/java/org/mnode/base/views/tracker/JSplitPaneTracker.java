/*
 * $Id: JSplitPanePrefs.java [17-Mar-2004 00:17:20]
 *
 * Copyright (c) 2004 b.fore Solutions
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
