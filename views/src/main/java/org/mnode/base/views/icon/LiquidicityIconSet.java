/**
 * 
 */
package org.mnode.base.views.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ben
 *
 */
public class LiquidicityIconSet implements IconSet {

    private static final Logger LOG = LoggerFactory.getLogger(LiquidicityIconSet.class);
    
    private Map<String, Icon> icons;
    
    /**
     * 
     */
    public LiquidicityIconSet() {
        icons = new HashMap<String, Icon>();
    }
    
    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getAuthor()
     */
    @Override
    public String getAuthor() {
        return "GoSquared";
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getDescription()
     */
    @Override
    public String getDescription() {
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getDisabledIcon(java.lang.String)
     */
    @Override
    public Icon getDisabledIcon(String key) {
        return getIcon(key + "_disabled");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getIcon(java.lang.String)
     */
    @Override
    public Icon getIcon(String key) {
        Icon icon = icons.get(key);
        if (icon == null) {
            try {
                URL iconUrl = getClass().getResource("/icons/liquidicity/" + key + ".png");
                if (iconUrl != null) {
                    icon = new ImageIcon(iconUrl);
                    icons.put(key, icon);
                }
            }
            catch (Exception e) {
                LOG.warn("Icon [" + key + "] not found.", e);
            }
        }
        return icon;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getId()
     */
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getPressedIcon(java.lang.String)
     */
    @Override
    public Icon getPressedIcon(String key) {
        return getIcon(key + "_pressed");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getRolloverIcon(java.lang.String)
     */
    @Override
    public Icon getRolloverIcon(String key) {
        return getIcon(key + "_rollover");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#getSelectedIcon(java.lang.String)
     */
    @Override
    public Icon getSelectedIcon(String key) {
        return getIcon(key + "_selected");
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.IconSet#isPressedIconGenerated()
     */
    @Override
    public boolean isPressedIconGenerated() {
        // TODO Auto-generated method stub
        return false;
    }

}
