/**
 * 
 */
package org.mnode.base.views.action;

import java.awt.event.ActionEvent;

import org.jdesktop.swingx.action.ActionManager;
import org.mnode.base.views.AbstractTabbedView;

/**
 * @author fortuna
 *
 */
public class CloseOtherTabs<T extends AbstractTabbedView<K,?>, K> extends AbstractTabAction<T, K> {
	
	/**
     * 
     */
    private static final long serialVersionUID = 3857936811611961784L;

    /**
	 * 
	 */
	public CloseOtherTabs(String id) {
		super("Close Others", id);
		setEnabled(false);
        ActionManager.getInstance().addAction(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getView().closeOtherTabs(getTabKey());
	}
}
