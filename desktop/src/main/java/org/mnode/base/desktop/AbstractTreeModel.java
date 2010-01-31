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
/**
 *
 */
package org.mnode.base.desktop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * An abstract tree model implementation.
 * 
 * @author Ben Fortuna
 */
public abstract class AbstractTreeModel implements TreeModel {

    private final List<TreeModelListener> listeners;

    /**
     * Default constructor.
     */
    public AbstractTreeModel() {
        listeners = new ArrayList<TreeModelListener>();
    }

    /**
     * {@inheritDoc}
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * @param path the changed path
     */
    protected final void fireTreeStructureChanged(Object[] path) {
        final TreeModelEvent e = new TreeModelEvent(this, path);
        for (TreeModelListener l : listeners) {
            l.treeStructureChanged(e);
        }
    }
    
    protected final void fireTreeNodesInserted(Object[] path, int[] childIndices, Object[] children) {
        final TreeModelEvent e = new TreeModelEvent(this, path, childIndices, children);
        for (TreeModelListener l : listeners) {
            l.treeNodesInserted(e);
        }
    }
    
    protected final void fireTreeNodesRemoved(Object[] path, int[] childIndices, Object[] children) {
        final TreeModelEvent e = new TreeModelEvent(this, path, childIndices, children);
        for (TreeModelListener l : listeners) {
            l.treeNodesRemoved(e);
        }
    }
    
    protected final void fireTreeNodesChanges(Object[] path, int[] childIndices, Object[] children) {
        final TreeModelEvent e = new TreeModelEvent(this, path, childIndices, children);
        for (TreeModelListener l : listeners) {
            l.treeNodesChanged(e);
        }
    }
}
