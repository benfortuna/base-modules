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
import java.util.Collection;
import java.util.Set;

import javax.swing.JTabbedPane;

import org.jvnet.substance.api.tabbed.VetoableMultipleTabCloseListener;

/**
 * @author Ben
 *
 */
public class VetoableMultipleTabCloseListenerImpl implements VetoableMultipleTabCloseListener {

    private final Collection<Component> vetoedTabs;
    
    public VetoableMultipleTabCloseListenerImpl(Collection<Component> tabs) {
        this.vetoedTabs = tabs;
    }
    /**
     * {@inheritDoc}
     */
    public boolean vetoTabsClosing(JTabbedPane arg0, Set<Component> tabComponents) {
        tabComponents.removeAll(vetoedTabs);
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void tabsClosed(JTabbedPane arg0, Set<Component> arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void tabsClosing(JTabbedPane arg0, Set<Component> arg1) {
        // TODO Auto-generated method stub

    }

}
