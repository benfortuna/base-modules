/**
 * 
 */
package org.mnode.base.views.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.jdesktop.swingx.action.ActionManager;
import org.mnode.base.views.AbstractTabbedView;

/**
 * @author fortuna
 *
 */
public class CloseTab<T extends AbstractTabbedView<K,?>, K> extends AbstractTabAction<T, K> {

	/**
     * 
     */
    private static final long serialVersionUID = 1075084627405360036L;
	
	/**
	 * 
	 */
	public CloseTab(String id) {
		super("Close Tab", id);
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		setEnabled(false);
        ActionManager.getInstance().addAction(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getView().closeTab(getTabKey());
	}
}
