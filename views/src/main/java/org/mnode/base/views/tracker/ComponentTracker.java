/*
 * $Id: ComponentPrefs.java [17-Mar-2004 00:05:56]
 *
 * Copyright (c) 2004 b.fore Solutions
 */
package org.mnode.base.views.tracker;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

/**
 * Tracks component changes.
 * @author Ben Fortuna
 */
public class ComponentTracker implements ComponentListener {

    private Preferences preferences;

    private Component component;

    private String id;

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param component a component to monitor for preference
     * changes
     */
    public ComponentTracker(final Component component) {
        this(component, component.getName());
    }

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param component a component to monitor for preference
     * changes
     * @param id the identifier of a specific component
     */
    public ComponentTracker(final Component component, final String id) {
        this.component = component;
        this.id = id;

        preferences = Preferences.userNodeForPackage(component.getClass());

        component.setLocation(getLocation());
        component.setSize(getSize());
//        component.setVisible(isVisible());

        component.addComponentListener(this);
    }

    /**
     * @return
     */
    public String getUniqueId() {
    	if (id != null) {
        	return component.getClass().getName() + "." + id;
    	}
    	return component.getClass().getName();
    }

    /**
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(final ComponentEvent e) {
    }

    /**
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public void componentMoved(final ComponentEvent e) {
        // save preferences on component location..
        getPreferences().putInt(getUniqueId() + ".x", component.getX());
        getPreferences().putInt(getUniqueId() + ".y", component.getY());
    }

    /**
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    public void componentResized(final ComponentEvent e) {
        // save preferences on component size..
        getPreferences().putInt(getUniqueId() +  ".width",
                component.getWidth());
        getPreferences().putInt(getUniqueId() + ".height",
                component.getHeight());
    }

    /**
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(final ComponentEvent e) {
    }

    /**
     * Returns the saved location preferences for the component.
     * @return a point specifying a location
     */
    public final Point getLocation() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(Math.min(screenSize.width - getSize().width,
        		getPreferences().getInt(getUniqueId() + ".x",
                        component.getLocation().x)),
                        Math.min(screenSize.height - getSize().height,
                getPreferences().getInt(getUniqueId() + ".y",
                        component.getLocation().y)));
    }

    /**
     * Returns the saved size preferences for the component.
     * @return a dimension specifying a size
     */
    public final Dimension getSize() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(Math.min(screenSize.width,
        		getPreferences().getInt(getUniqueId() + ".width",
                        component.getSize().width)),
                        Math.min(screenSize.height,
                getPreferences().getInt(getUniqueId() + ".height",
                        component.getSize().height)));
    }
    
    /**
     * @return Returns the component.
     */
    public final Component getComponent() {
        return component;
    }

    /**
     * @return Returns the id.
     */
    public final String getId() {
        return id;
    }

    /**
     * @return Returns the preferences.
     */
    public final Preferences getPreferences() {
        return preferences;
    }
}
