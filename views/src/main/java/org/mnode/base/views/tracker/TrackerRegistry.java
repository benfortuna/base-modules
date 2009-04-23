/*
 * $Id: PrefsManager.java [08-Jun-2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package org.mnode.base.views.tracker;

import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSplitPane;

import org.jdesktop.swingx.JXStatusBar;
import org.mnode.base.views.ToggleViewFrame;

/**
 * Manages the monitoring of user interface preferences.
 * @author benfortuna
 */
public final class TrackerRegistry {

    private static TrackerRegistry instance = new TrackerRegistry();

    private Map<String, ComponentTracker> componentTrackers;

    private Map<String, JSplitPaneTracker> jSplitPaneTrackers;
    
    private Map<String, FrameTracker> frameTrackers;
    
    private Map<String, ToggleViewFrameTracker> toggleFrameTrackers;
    
    private Map<String, JXStatusBarTracker> jxStatusBarTrackers;

    /**
     * Constructor made private to enforce singleton.
     */
    private TrackerRegistry() {
        componentTrackers = new HashMap<String, ComponentTracker>();
        jSplitPaneTrackers = new HashMap<String, JSplitPaneTracker>();
        frameTrackers = new HashMap<String, FrameTracker>();
        toggleFrameTrackers = new HashMap<String, ToggleViewFrameTracker>();
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
     * @param component a component to monitor
     * @param id the id of a specific component
     */
    public void register(final Component component, final String id) {
    	ComponentTracker tracker = new ComponentTracker(component, id);
    	componentTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JSplitPane for preferences monitoring.
     * @param pane a JSplitPane to monitor
     * @param id the id of a specific JSplitPane
     */
    public void register(final JSplitPane pane, final String id) {
    	JSplitPaneTracker tracker = new JSplitPaneTracker(pane, id);
        jSplitPaneTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a Frame for preferences monitoring.
     * @param frame a Frame to monitor
     * @param id the id of a specific Frame
     */
    public void register(final Frame frame, final String id) {
        FrameTracker tracker = new FrameTracker(frame, id);
        frameTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a ToggleViewFrame for preferences monitoring.
     * @param frame a ToggleViewFrame to monitor
     * @param id the id of a specific ToggleViewFrame
     */
    public void register(final ToggleViewFrame frame, final String id) {
        ToggleViewFrameTracker tracker = new ToggleViewFrameTracker(frame, id);
        toggleFrameTrackers.put(tracker.getUniqueId(), tracker);
    }

    /**
     * Registers a JXStatusBar for preferences monitoring.
     * @param frame a ToggleViewFrame to monitor
     * @param id the id of a specific ToggleViewFrame
     */
    public void register(final JXStatusBar statusBar, final String id) {
        JXStatusBarTracker tracker = new JXStatusBarTracker(statusBar, id);
        jxStatusBarTrackers.put(tracker.getUniqueId(), tracker);
    }
}
