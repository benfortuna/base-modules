/*
 * $Id: ShowStatusBarAction.java Exp $
 *
 * Created: [23/05/2007]
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
package org.mnode.base.views.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.action.AbstractActionExt;
import org.jdesktop.swingx.action.ActionManager;

/**
 * @author Ben
 *
 */
public class ShowStatusBarAction extends AbstractActionExt {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1280103703456810418L;

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle(
            "org.mnode.base.views.messages");

    private List<JXStatusBar> statusBars;

    /**
     * Default constructor.
     */
    public ShowStatusBarAction(String id) {
        super(MESSAGES.getString("action.showStatusBar"), id);
        setStateAction(true);
        statusBars = new ArrayList<JXStatusBar>();
        ActionManager.getInstance().addAction(this);
    }
    
    /**
     * @param statusBar
     */
    public void addStatusBar(JXStatusBar statusBar) {
    	statusBars.add(statusBar);
        setSelected(statusBars.get(0).isVisible());
        statusBar.setVisible(isSelected());
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
    	for (JXStatusBar statusBar : statusBars) {
            statusBar.setVisible(isSelected());
    	}
    }
}
