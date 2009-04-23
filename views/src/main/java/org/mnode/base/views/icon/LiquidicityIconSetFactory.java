/**
 * 
 */
package org.mnode.base.views.icon;


/**
 * @author Ben
 *
 */
public class LiquidicityIconSetFactory extends AbstractIconSetFactory {

    private IconSet defaultIconSet;
    
    /**
     * 
     */
    public LiquidicityIconSetFactory() {
        defaultIconSet = new LiquidicityIconSet();
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getAvailableIds()
     */
    @Override
    public String[] getAvailableIds() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getDefaultIconSet()
     */
    @Override
    public IconSet getDefaultIconSet() {
        return defaultIconSet;
    }

    /* (non-Javadoc)
     * @see net.modularity.desktop.icon.AbstractIconSetFactory#getIconSet(java.lang.String)
     */
    @Override
    public IconSet getIconSet(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
