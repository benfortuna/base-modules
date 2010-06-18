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
package org.mnode.base.substance;

import java.awt.Component;

import javax.swing.JTabbedPane;

import org.jvnet.substance.api.tabbed.VetoableTabCloseListener;

/**
 * @author Ben
 *
 */
public class VetoableTabCloseListenerImpl implements VetoableTabCloseListener {

    private final Component vetoedTab;
    
    public VetoableTabCloseListenerImpl(Component tab) {
        this.vetoedTab = tab;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean vetoTabClosing(JTabbedPane arg0, Component tab) {
        return tab.equals(vetoedTab);
    }

    /**
     * {@inheritDoc}
     */
    public void tabClosed(JTabbedPane arg0, Component arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void tabClosing(JTabbedPane arg0, Component arg1) {
        // TODO Auto-generated method stub

    }

}
