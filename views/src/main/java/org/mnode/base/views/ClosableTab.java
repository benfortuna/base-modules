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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

/**
 * @author fortuna
 *
 */
public abstract class ClosableTab extends JXPanel {

	/**
     * 
     */
    private static final long serialVersionUID = -2298055637594055908L;

    private JXLabel titleLabel;
	
	private JXButton closeButton;
	
	private JPopupMenu contextMenu;
	
	private JTabbedPane parent;
	
	/**
	 * @param title
	 */
	public ClosableTab(String title, JTabbedPane parent) {
	    this(title, null, parent);
	}
	
    /**
     * @param title
     * @param icon
     */
    public ClosableTab(String title, Icon icon, final JTabbedPane parent) {
		super(new BorderLayout());
		setOpaque(false);
		
		this.parent = parent;
		
		setToolTipText(title);
		
		titleLabel = new JXLabel(StringUtils.abbreviate(title, 20));
		titleLabel.setIcon(icon);
		titleLabel.setOpaque(false);
		add(titleLabel, BorderLayout.WEST);
		
		closeButton = new JXButton("x");
		closeButton.setOpaque(false);
//		closeButton.setPreferredSize(new Dimension(10, 10));
		closeButton.setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
		closeButton.setBorderPainted(false);
		closeButton.setMargin(null);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
			}
		});
		add(closeButton, BorderLayout.EAST);
		
		contextMenu = new JPopupMenu();
		contextMenu.add(new AbstractAction("Close") {
		    public void actionPerformed(ActionEvent e) {
		        onClose();
		    }
		});
        contextMenu.add(new AbstractAction("Close Others") {
            public void actionPerformed(ActionEvent e) {
                onCloseOthers();
            }
        });
        contextMenu.add(new AbstractAction("Close All") {
            public void actionPerformed(ActionEvent e) {
                onCloseAll();
            }
        });
        setComponentPopupMenu(contextMenu);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                parent.dispatchEvent(SwingUtilities.convertMouseEvent(ClosableTab.this, e, parent));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                parent.dispatchEvent(SwingUtilities.convertMouseEvent(ClosableTab.this, e, parent));
            }
            @Override
            public void mousePressed(MouseEvent e) {
//            	if (MouseInfo.getNumberOfButtons() == 3 && e.getButton() == MouseEvent.BUTTON2) {
                if (e.getButton() == MouseEvent.BUTTON2) {
            		onClose();
            	}
            	else {
                    parent.dispatchEvent(SwingUtilities.convertMouseEvent(ClosableTab.this, e, parent));
            	}
            }
        });
        
//        JXLayer<JXLabel> l = new JXLayer<JXLabel>(titleLabel);
//        l.setUI(new ContextMenuUI<JXLabel>(contextMenu));
//        add(l, BorderLayout.WEST);
        
        /*
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    contextMenu.show(ClosableTab.this, e.getX(), e.getY());
                }
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    contextMenu.show(ClosableTab.this, e.getX(), e.getY());
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setVisible(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setVisible(false);
            }
        });
        */
	}

	/**
	 * 
	 */
	public abstract void onClose();
	
	/**
	 * 
	 */
	public abstract void onCloseOthers();
	
	/**
	 * 
	 */
	public abstract void onCloseAll();
	
    @Override
    public JToolTip createToolTip() {
        int tabIndex = parent.indexAtLocation(parent.getMousePosition().x, parent.getMousePosition().y);
        if (tabIndex >= 0 && tabIndex != parent.getSelectedIndex()) {
            Component c = parent.getComponentAt(tabIndex);
            
            final BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();
            graphics.setColor(c.getBackground());
            graphics.fillRect(0, 0, c.getWidth(), c.getHeight());
            c.paint(graphics);
            graphics.dispose();

            final int thumbnailWidth = image.getWidth() / 2;
            final int thumbnailHeight = image.getHeight() / 2;
            
//            Icon thumbnail = new ImageIcon(image.getScaledInstance(image.getWidth() / 2, -1, Image.SCALE_FAST));
            JToolTip toolTip = new JToolTip() {
                @Override
                public void paint(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.setColor(getBackground());
                    g2d.fillRect(0, 0, thumbnailWidth, thumbnailHeight);

                    paintBorder(g2d);

                    Insets insets = getInsets();
                    g2d.drawImage(image, insets.left, insets.top, thumbnailWidth - (insets.left + insets.right),
                            thumbnailHeight - (insets.top + insets.bottom), null);
                }
            };
//            toolTip.setComponent(new JXLabel(thumbnail));
            toolTip.setPreferredSize(new Dimension(thumbnailWidth, thumbnailHeight));
            toolTip.setBorder(BorderFactory.createLineBorder(UIManager.getColor("PopupMenu.border")));

            return toolTip;
        }
        return super.createToolTip();
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JTabbedPane tabs = new JTabbedPane();
		
		final JXPanel p = new JXPanel();
		tabs.addTab(null, p);
		tabs.setTabComponentAt(0, new ClosableTab("Tab", tabs) {
			@Override
			public void onClose() {
				tabs.removeTabAt(tabs.indexOfComponent(p));
			}
			@Override
			public void onCloseAll() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onCloseOthers() {
				// TODO Auto-generated method stub
				
			}
		});
		
		JXFrame f = new JXFrame(ClosableTab.class.getSimpleName());
		f.add(tabs);
		f.setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}
