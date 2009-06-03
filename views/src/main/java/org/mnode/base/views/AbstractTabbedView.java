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
package org.mnode.base.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @param <K> the key for views in this container
 * @param <V> the view type held by this container
 * 
 * @author fortuna
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractTabbedView<K, V extends AbstractView> extends AbstractView implements
        PropertyChangeListener {

    private Map<K, V> views;

    private JTabbedPane tabs;

    /**
     * @param id the identifier for this view
     * @param title the title for this view
     */
    public AbstractTabbedView(String id, String title) {
        this(id, title, null);
    }

    /**
     * @param id the identifier for this view
     * @param title the title for this view
     * @param icon the icon for this view
     */
    public AbstractTabbedView(String id, String title, ImageIcon icon) {
        super(id, title, icon);

        views = new HashMap<K, V>();

        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (K key : views.keySet()) {
                    if (key.equals(getCurrentKey())) {
                        setStatusMessage(views.get(key).getStatusMessage());
                        setProgress(views.get(key).getProgress());
                        views.get(key).addPropertyChangeListener(AbstractTabbedView.this);
                    } else {
                        views.get(key).removePropertyChangeListener(AbstractTabbedView.this);
                    }
                }
            }
        });
        add(tabs, BorderLayout.CENTER);
    }

    /**
     * @return a map of all views held in this container
     */
    public Map<K, V> getViews() {
        return views;
    }

    /**
     * @return the key for the currently active view
     */
    public K getCurrentKey() {
        for (K key : views.keySet()) {
            if (views.get(key).equals(tabs.getSelectedComponent())) {
                return key;
            }
        }
        return null;
    }

    /**
     * @param key the key for the view to return
     * @return the view associated with the specified key, or null if no such view exists
     */
    public V getView(final K key) {
        V view = views.get(key);
        if (view == null) {
            view = createView(key);
            views.put(key, view);
            tabs.addTab(null, view);
            final ClosableTab tab = new ClosableTab(view.getTitle(), view.getIcon(), tabs) {
                @Override
                public void onClose() {
                    closeTab(key);
                }

                @Override
                public void onCloseAll() {
                    closeAllTabs();
                }

                @Override
                public void onCloseOthers() {
                    closeOtherTabs(key);
                }
            };
            tabs.setTabComponentAt(tabs.indexOfComponent(view), tab);
        }
        return view;
    }

    /**
     * @param key the key of the view to make active
     */
    public void showView(final K key) {
        SwingWorker<V, Object> showViewWorker = new SwingWorker<V, Object>() {
            @Override
            protected V doInBackground() throws Exception {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                // setStatusMessage("Loading..");

                return getView(key);
            }

            @Override
            protected void done() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                // setStatusMessage("Ready.");

                try {
                    tabs.setSelectedComponent(get());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        showViewWorker.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if ("statusMessage".equals(e.getPropertyName())) {
            setStatusMessage((String) e.getNewValue());
        } else if ("progress".equals(e.getPropertyName())) {
            setProgress((Integer) e.getNewValue());
        }
    }

    /**
     * Subscribes a listener to tab change events.
     * @param l a listener for tab change events
     */
    public void addTabChangeListener(ChangeListener l) {
        tabs.addChangeListener(l);
    }

    /**
     * Unsubscribes a listener from tab change events.
     * @param l a listener for tab change events
     */
    public void removeTabChangeListener(ChangeListener l) {
        tabs.removeChangeListener(l);
    }

    /**
     * Creates a new view for use by this container.
     * @param key the key of the new view
     * @return a new view instance associated with the specified key
     */
    protected abstract V createView(K key);

    /**
     * Closes the tab of the view with the specified key.
     * @param key the key of the view to close
     */
    public void closeTab(K key) {
        V view = views.remove(key);
        view.close();
        tabs.removeTabAt(tabs.indexOfComponent(view));
    }

    /**
     * 
     */
    public void closeAllTabs() {
        for (V view : views.values()) {
            view.close();
        }
        views.clear();
        for (int i = tabs.getTabCount() - 1; i >= 0; i--) {
            tabs.removeTabAt(i);
        }
    }

    /**
     * Closes all view tabs except the one associated with the specified key.
     * @param key the key of the view that should not be closed
     */
    public void closeOtherTabs(K key) {
        List<K> removals = new ArrayList<K>();
        for (K k : views.keySet()) {
            if (!k.equals(key)) {
                removals.add(k);
            }
        }
        for (K k : removals) {
            closeTab(k);
        }
    }

}
