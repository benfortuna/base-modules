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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Window;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXPanel;
import org.mnode.base.views.action.CloseViewAction;
import org.mnode.base.views.tracker.TrackerRegistry;

/**
 * Abstract base implementation for views. Default view layout is a {@link BorderLayout}.
 * 
 * @author Ben Fortuna
 */
public abstract class AbstractView extends JXPanel implements View {

    private static final GradientPaint BLUE_EXPERIENCE = new GradientPaint(new Point2D.Double(0, 0), new Color(168,
            204, 241), new Point2D.Double(0, 1), new Color(44, 61, 146));

    private String id;

    private String title;

    private ImageIcon icon;

    private JMenuBar menuBar;

    private Dimension maximumSize;

    private CloseViewAction closeViewAction;

    private ViewStatusBar statusBar;

    // private JMenuItem showStatusBarItem;

    private String statusMessage;

    private int progress;

    /**
     * @param id the identifier for this view 
     * @param title the title for this view
     */
    public AbstractView(String id, String title) {
        this(id, title, null);
    }

    /**
     * @param id the identifier for this view 
     * @param title the title for this view
     * @param icon the icon for this view
     */
    public AbstractView(String id, String title, ImageIcon icon) {
        super(new BorderLayout());
        // setBackgroundPainter(new GlossPainter<Component>());
        // new BasicGradientPainter(BasicGradientPainter.BLUE_EXPERIENCE));

        // GlossPainter gloss = new GlossPainter();

        // PinstripePainter stripes = new PinstripePainter();
        // stripes.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.17f));
        // stripes.setSpacing(5.0);

        // MattePainter matte = new MattePainter(new Color(51, 51, 51));

        // setBackgroundPainter(new CompoundPainter(matte, gloss));
        // setBackgroundPainter(new MattePainter<Component>(BLUE_EXPERIENCE));

        this.id = id;
        this.title = title;
        this.icon = icon;

        this.progress = 0;

        closeViewAction = new CloseViewAction();

        // statusBar = new ViewStatusBar(this);
        // statusBar.setVisible(false);

        // add(statusBar, BorderLayout.SOUTH);

        // ActionContainerFactory actionFactory = new ActionContainerFactory();
        // showStatusBarItem = actionFactory.createMenuItem(
        // new ShowStatusBarAction(statusBar));
    }

    /**
     * @param context
     */
    /*
     * protected void activate(ComponentContext context) { menuBar = (ViewMenuBar) Components.newInstance(
     * "ViewMenuBar", null, context.getBundleContext()); menuBar.getFileMenu().insert(closeViewAction, 0); //
     * menuBar.getFileMenu().insertSeparator(1); menuBar.getViewMenu().insert(showStatusBarItem, 0); }
     */

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public final void setTitle(String title) {
        String oldTitle = this.title;

        this.title = title;
        firePropertyChange("title", oldTitle, title);
    }

    /**
     * {@inheritDoc}
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon associated with this view
     */
    public void setIcon(ImageIcon icon) {
        ImageIcon oldIcon = this.icon;
        this.icon = icon;
        firePropertyChange("icon", oldIcon, icon);
    }

    /**
     * @param menuBar the menu bar to show with this view
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * @return the menuBar
     */
    public final JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * @return the statusBar
     */
    public ViewStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @return the closeViewAction
     */
    public final CloseViewAction getCloseViewAction() {
        return closeViewAction;
    }

    /**
     * @return the window containing this view
     */
    public Window getWindow() {
        return SwingUtilities.getWindowAncestor(this);
    }

    /**
     * {@inheritDoc}
     */
    public Component getViewComponent() {
        return this;
    }

    /**
     * @return the statusMessage
     */
    public final String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    public final void setStatusMessage(String statusMessage) {
        String oldStatusMessage = this.statusMessage;
        this.statusMessage = statusMessage;
        if (getStatusBar() != null) {
            getStatusBar().setMessage(statusMessage);
        }
        firePropertyChange("statusMessage", oldStatusMessage, statusMessage);
    }

    /**
     * @param statusBar
     *            the statusBar to set
     */
    public final void setStatusBar(ViewStatusBar statusBar) {
        this.statusBar = statusBar;
        TrackerRegistry.getInstance().register(statusBar, id + ".statusBar");
    }

    /**
     * @return the progress
     */
    public final int getProgress() {
        return progress;
    }

    /**
     * @param progress
     *            the progress to set
     */
    public final void setProgress(int progress) {
        int oldProgress = this.progress;
        this.progress = progress;
        firePropertyChange("progress", oldProgress, progress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        // Default implementation does nothing..
    }
}
