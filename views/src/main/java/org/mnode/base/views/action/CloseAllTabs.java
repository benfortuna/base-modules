/**
 * 
 */
package org.mnode.base.views.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.jdesktop.swingx.action.ActionManager;
import org.mnode.base.views.AbstractTabbedView;

/**
 * @author fortuna
 *
 */
public class CloseAllTabs<T extends AbstractTabbedView<K,?>, K> extends AbstractTabAction<T, K> {
	
	/**
     * 
     */
    private static final long serialVersionUID = -9095289355909314277L;

    /**
	 * 
	 */
	public CloseAllTabs(String id) {
		super("Close All", id);
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | InputEvent.SHIFT_DOWN_MASK));
		setEnabled(false);
        ActionManager.getInstance().addAction(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		getView().closeAllTabs();
	}
}
