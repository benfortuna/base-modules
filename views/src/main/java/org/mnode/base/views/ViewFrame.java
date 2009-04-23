/*
 * $Id: ViewFrame.java Exp $
 *
 * Created: [06/04/2007]
 *
 * Copyright (c) 2007, Ben Fortuna
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

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXFrame;
import org.mnode.base.views.tracker.TrackerRegistry;

/**
 * @author Ben
 *
 */
public class ViewFrame<V extends View> extends JXFrame {

	private V view;
	
    /**
     * 
     */
    private static final long serialVersionUID = -4529231089491229015L;

    /**
     * @param view
     */
    public ViewFrame(V view) {
        super(view.getTitle());
        if (view.getIcon() != null) {
            setIconImage(view.getIcon().getImage());
        }
        setJMenuBar(view.getMenuBar());
        setMaximumSize(view.getMaximumSize());
        
        add(view.getViewComponent(), BorderLayout.CENTER);
        
//        view.getCloseViewAction().setFrame(this);
        
        setJMenuBar(view.getMenuBar());
        setStatusBar(view.getStatusBar());
        pack();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // register tracker for window size/location memory..
        TrackerRegistry.getInstance().register(this, view.getId());
        
        view.addPropertyChangeListener("title", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                setTitle((String) e.getNewValue());
            }
        });
        view.addPropertyChangeListener("icon", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                setIconImage(((ImageIcon) e.getNewValue()).getImage());
            }
        });
        
        this.view = view;
    }

	/**
	 * @return the view
	 */
	public final V getView() {
		return view;
	}
}
