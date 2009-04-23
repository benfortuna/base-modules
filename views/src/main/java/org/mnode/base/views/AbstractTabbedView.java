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
 * @author fortuna
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractTabbedView<K, V extends AbstractView> extends AbstractView implements PropertyChangeListener {

	private Map<K, V> views;
	
	private JTabbedPane tabs;
	
	/**
	 * @param id
	 * @param title
	 */
	public AbstractTabbedView(String id, String title) {
		this(id, title, null);
	}

	/**
	 * @param id
	 * @param title
	 * @param icon
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
                    }
                    else {
                        views.get(key).removePropertyChangeListener(AbstractTabbedView.this);
                    }
                }
            }
        });
        add(tabs, BorderLayout.CENTER);
	}

	/**
	 * @return
	 */
	public Map<K, V> getViews() {
		return views;
	}
	
	/**
	 * @return
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
	 * @param key
	 * @return
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
     * @param key
     */
    public void showView(final K key) {
        SwingWorker<V, Object> showViewWorker = new SwingWorker<V, Object>() {
            @Override
            protected V doInBackground() throws Exception {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                setStatusMessage("Loading..");
                
                return getView(key);
            }
            
            @Override
            protected void done() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                setStatusMessage("Ready.");
                
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
    
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if ("statusMessage".equals(e.getPropertyName())) {
            setStatusMessage((String) e.getNewValue());
        }
        else if ("progress".equals(e.getPropertyName())) {
            setProgress((Integer) e.getNewValue());
        }
    }
    
    /**
     * @param l
     */
    public void addTabChangeListener(ChangeListener l) {
    	tabs.addChangeListener(l);
    }
    
    /**
     * @param l
     */
    public void removeTabChangeListener(ChangeListener l) {
    	tabs.removeChangeListener(l);
    }
    
    /**
     * @param key
     * @return
     */
    protected abstract V createView(K key);
    
    /**
     * @param calendar
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
     * @param calendar
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
