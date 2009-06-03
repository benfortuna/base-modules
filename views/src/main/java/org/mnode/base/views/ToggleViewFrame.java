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
package org.mnode.base.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.mnode.base.views.tracker.TrackerRegistry;

/**
 * @author Ben
 * 
 */
public class ToggleViewFrame extends JXFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = -9219831694981041264L;

    /**
     * Available view modes.
     * 
     * @author fortuna
     *
     */
    public enum Mode {
        
        /**
         * The normal view mode.
         */
        FULL,
        
        /**
         * A compact view mode.
         */
        MINIMAL
    };

    private Mode toggleMode;

    // private JToggleButton toggleButton;

    private int miniWidth;

    private int fullWidth;

    private String title;

    private View mainView;

    private View sideView;

    /**
     * @param title the frame title
     * @param mainView the primary view, which is not visible in compact mode
     * @param sideView the side view
     */
    public ToggleViewFrame(final String title, View mainView, View sideView) {
        super(title);
        this.title = title;
        this.mainView = mainView;
        this.sideView = sideView;

        miniWidth = 0;
        fullWidth = 0;
        /*
         * mainPane = new JXPanel(); mainPane.setVisible(false); mainPane.setBackgroundPainter(new
         * GlossPainter<Component>( new GradientPaint( new Point2D.Double(0, 0), new Color(168, 204, 241), new
         * Point2D.Double(0, 1), new Color(44, 61, 146))));
         */
        add(mainView.getViewComponent(), BorderLayout.CENTER);

        /*
         * toggleButton = new JToggleButton("F"); toggleButton.addItemListener(new ItemListener() { public void
         * itemStateChanged(ItemEvent e) { if (e.getStateChange() == ItemEvent.SELECTED) { setToggleMode(Mode.FULL); }
         * else { setToggleMode(Mode.MINIMAL); } } }); sidePane = new JXPanel(new FlowLayout());
         * sidePane.add(toggleButton);
         */
        add(sideView.getViewComponent(), BorderLayout.EAST);

        setSize(miniWidth, 700);

        // register tracker for window size/location memory..
        TrackerRegistry.getInstance().register(this, getName());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // if frame is maximised don't update the saved width..
                if (Frame.MAXIMIZED_BOTH != getExtendedState()) {
                    if (Mode.MINIMAL.equals(getToggleMode())) {
                        setMiniWidth(getSize().width);
                    } else {
                        setFullWidth(getSize().width);
                    }
                }
            }
        });

        // copy title and icon from main view..
        // setTitle(mainView.getTitle() + " - " + title);
        // setIconImage(mainView.getIcon().getImage());

        mainView.addPropertyChangeListener("title", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (Mode.FULL.equals(getToggleMode())) {
                    setTitle((String) e.getNewValue() + " - " + title);
                }
            }
        });
        mainView.addPropertyChangeListener("icon", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                setIconImage(((ImageIcon) e.getNewValue()).getImage());
            }
        });
    }

    /**
     * @param newMode the new mode to enable
     */
    public void setToggleMode(Mode newMode) {
        // if frame is maximised do nothing..
        if (Frame.MAXIMIZED_BOTH == getExtendedState()) {
            return;
        }
        // if mode unchanged do nothing..
        else if (newMode.equals(toggleMode)) {
            return;
        }

        Mode oldMode = this.toggleMode;
        this.toggleMode = newMode;

        // setVisible(false);
        // getGlassPane().setVisible(true);
        if (Mode.MINIMAL.equals(toggleMode)) {
            // mainView.setVisible(false);
            remove(mainView.getViewComponent());
            // remove(sideView);
            // validate();
            add(sideView.getViewComponent(), BorderLayout.CENTER);
            setJMenuBar(sideView.getMenuBar());
            setStatusBar(sideView.getStatusBar());
            setMaximumSize(sideView.getMaximumSize());
            validate();
            // repaint();
            setSize(miniWidth, getSize().height);
            setLocation(getLocation().x + fullWidth - miniWidth, getLocation().y);
            sideView.getViewComponent().requestFocus();
            setTitle(title);
            setIconImage(sideView.getIcon().getImage());
        } else {
            // mainView.setVisible(true);
            // remove(sideView);
            // validate();
            add(mainView.getViewComponent(), BorderLayout.CENTER);
            add(sideView.getViewComponent(), BorderLayout.EAST);
            setJMenuBar(mainView.getMenuBar());
            setStatusBar(mainView.getStatusBar());
            setMaximumSize(null);
            validate();
            // repaint();
            setSize(fullWidth, getSize().height);
            setLocation(getLocation().x - fullWidth + miniWidth, getLocation().y);
            // if (!toggleButton.isSelected()) {
            // toggleButton.setSelected(true);
            // }
            setTitle(mainView.getTitle() + " - " + title);
            setIconImage(mainView.getIcon().getImage());
        }
        // setVisible(true);
        // getGlassPane().setVisible(false);
        firePropertyChange("toggleMode", oldMode, newMode);
    }

    /**
     * @return the toggleMode
     */
    public final Mode getToggleMode() {
        return toggleMode;
    }

    /**
     * @return the miniWidth
     */
    public final int getMiniWidth() {
        return miniWidth;
    }

    /**
     * @param miniWidth
     *            the miniWidth to set
     */
    public final void setMiniWidth(int miniWidth) {
        int oldMiniWidth = this.miniWidth;
        this.miniWidth = miniWidth;
        sideView.getViewComponent().setPreferredSize(
                new Dimension(miniWidth - getSize().width + getContentPane().getSize().width, getSize().height));
        if (Mode.MINIMAL.equals(toggleMode)) {
            setSize(miniWidth, getSize().height);
        }
        firePropertyChange("miniWidth", oldMiniWidth, miniWidth);
    }

    /**
     * @return the fullWidth
     */
    public final int getFullWidth() {
        return fullWidth;
    }

    /**
     * @param fullWidth
     *            the fullWidth to set
     */
    public final void setFullWidth(int fullWidth) {
        int oldFullWidth = this.fullWidth;
        this.fullWidth = fullWidth;
        if (Mode.FULL.equals(toggleMode)) {
            setSize(fullWidth, getSize().height);
        }
        firePropertyChange("fullWidth", oldFullWidth, fullWidth);
    }

    /**
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        JToggleButton toggleButton = new JToggleButton("F");

        AbstractView sideView = new AbstractView("side", "Side Pane") {
        };
        sideView.setMaximumSize(new Dimension(300, 1024));

        JXPanel sidePane = new JXPanel(new FlowLayout());
        sidePane.add(toggleButton);
        sidePane.add(new JXButton("E"));
        sidePane.add(new JXButton("A"));
        sidePane.add(new JXButton("T"));
        sidePane.add(new JXButton("M"));
        sideView.add(sidePane);

        final ToggleViewFrame f = new ToggleViewFrame(ToggleViewFrame.class.getSimpleName(), new AbstractView("centre",
                "Centre") {
        }, sideView);
        toggleButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    f.setToggleMode(Mode.FULL);
                } else {
                    f.setToggleMode(Mode.MINIMAL);
                }
            }
        });
        f.setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
        // f.setLocation(0, 0);
        f.setVisible(true);
    }
}
