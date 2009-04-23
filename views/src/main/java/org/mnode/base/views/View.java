/*
 * $Id: View.java $
 *
 * Created: [12/07/2008]
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

package org.mnode.base.views;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;

import org.jdesktop.swingx.JXStatusBar;

/**
 * Implementors provide a frame-embeddable UI.
 * @author Ben
 *
 */
public interface View {

	/**
	 * @return a unique identifier for the view implementation
	 */
	String getId();
	
	/**
	 * @return a displayable title for the view, or null if no title is specified
	 */
	String getTitle();
	
	/**
	 * @return an icon for the view, or null if no icon is specified
	 */
	ImageIcon getIcon();
	
	/**
	 * @return a menu bar for the view, or null if no menu bar is specified
	 */
	JMenuBar getMenuBar();
	
	/**
	 * @return a status bar component
	 */
	JXStatusBar getStatusBar();
	
	/**
	 * @return
	 */
	String getStatusMessage();
	
	/**
	 * @return
	 */
	int getProgress();
	
	/**
	 * @return
	 */
	Dimension getMaximumSize();
	
	/**
	 * @return the frame-embeddable view component
	 */
	Component getViewComponent();
	
	/**
	 * Close the view and release any resources.
	 */
	void close();
	
	/**
	 * @param property
	 * @param l
	 */
	void addPropertyChangeListener(PropertyChangeListener l);
	
	/**
	 * @param property
	 * @param l
	 */
	void removePropertyChangeListener(PropertyChangeListener l);
	
	/**
	 * @param property
	 * @param l
	 */
	void addPropertyChangeListener(String property, PropertyChangeListener l);
	
	/**
	 * @param property
	 * @param l
	 */
	void removePropertyChangeListener(String property, PropertyChangeListener l);
}
