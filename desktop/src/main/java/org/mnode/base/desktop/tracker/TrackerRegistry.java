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

import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSplitPane;

import org.jdesktop.swingx.JXStatusBar;

/**
 * Manages the monitoring of user interface preferences.
 * 
 * @author benfortuna
 */
public final class TrackerRegistry {

    private static TrackerRegistry instance = new TrackerRegistry();

    private Map<String, ComponentTracker> componentTrackers;

    private Map<String, JSplitPaneTracker> jSplitPaneTrackers;

    private Map<String, FrameTracker> frameTrackers;

    private Map<String, JXStatusBarTracker> jxStatusBarTrackers;

    /**
     * Constructor made private to enforce singleton.
     */
    private TrackerRegistry() {
        componentTrackers = new HashMap<String, ComponentTracker>();
        jSplitPaneTrackers = new HashMap<String, JSplitPaneTracker>();
        frameTrackers = new HashMap<String, FrameTracker>();
        jxStatusBarTrackers = new HashMap<String, JXStatusBarTracker>();
    }

    /**
     * @return Returns the instance.
     */
    public static TrackerRegistry getInstance() {
        return instance;
    }

    /**
     * Registers a component for preferences monitoring.
     * 
     * @param component
     *            a component to monitor
     * @param id
     *            the id of a specific component
     */
    public void register(final Component component, final String id) {
        ComponentTracker tracker = new ComponentTracker(component, id);
        componentTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JSplitPane for preferences monitoring.
     * 
     * @param pane
     *            a JSplitPane to monitor
     * @param id
     *            the id of a specific JSplitPane
     */
    public void register(final JSplitPane pane, final String id) {
        JSplitPaneTracker tracker = new JSplitPaneTracker(pane, id);
        jSplitPaneTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a Frame for preferences monitoring.
     * 
     * @param frame
     *            a Frame to monitor
     * @param id
     *            the id of a specific Frame
     */
    public void register(final Frame frame, final String id) {
        FrameTracker tracker = new FrameTracker(frame, id);
        frameTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JXStatusBar for preferences monitoring.
     * @param statusBar
     *            a status bar to monitor
     * @param id
     *            the id of a specific status bar
     */
    public void register(final JXStatusBar statusBar, final String id) {
        JXStatusBarTracker tracker = new JXStatusBarTracker(statusBar, id);
        jxStatusBarTrackers.put(tracker.getUniqueId(), tracker);
    }
}
