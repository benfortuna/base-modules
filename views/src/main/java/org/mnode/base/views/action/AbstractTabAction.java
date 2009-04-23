/**
 * 
 */
package org.mnode.base.views.action;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.action.AbstractActionExt;
import org.mnode.base.views.AbstractTabbedView;

/**
 * @author Ben
 *
 */
public abstract class AbstractTabAction<T extends AbstractTabbedView<K, ?>, K> extends AbstractActionExt implements ChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = -3076365063730027079L;

    private T view;
    
    private K tabKey;

    /**
     * @param name
     * @param command
     */
    public AbstractTabAction(String name, String command) {
        super(name, command);
    }

    /**
     * @return the view
     */
    public final AbstractTabbedView<K, ?> getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public final void setView(final T view) {
        // remove old view listener..
        if (this.view != null) {
            this.view.removeTabChangeListener(this);
        }
        this.view = view;
        if (view != null) {
            tabKey = view.getCurrentKey();
            setEnabled(!view.getViews().isEmpty());
            view.addTabChangeListener(this);
        }
    }

    /**
     * @return the tabKey
     */
    public final K getTabKey() {
        return tabKey;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        tabKey = view.getCurrentKey();
        setEnabled(!view.getViews().isEmpty());
    }
}
