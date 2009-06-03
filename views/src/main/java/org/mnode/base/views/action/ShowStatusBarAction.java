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
     * @param id the action identifier
     */
    public ShowStatusBarAction(String id) {
        super(MESSAGES.getString("action.showStatusBar"), id);
        setStateAction(true);
        statusBars = new ArrayList<JXStatusBar>();
        ActionManager.getInstance().addAction(this);
    }
    
    /**
     * @param statusBar a status bar whose visibility is controlled by this action
     */
    public void addStatusBar(JXStatusBar statusBar) {
    	statusBars.add(statusBar);
        setSelected(statusBars.get(0).isVisible());
        statusBar.setVisible(isSelected());
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
    	for (JXStatusBar statusBar : statusBars) {
            statusBar.setVisible(isSelected());
    	}
    }
}
