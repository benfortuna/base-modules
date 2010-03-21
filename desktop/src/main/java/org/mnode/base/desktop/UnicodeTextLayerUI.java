/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2010, Ben Fortuna [fortuna@micronode.com]
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

package org.mnode.base.desktop;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.AbstractLayerUI;

/**
 * @author Ben
 *
 */
public class UnicodeTextLayerUI extends AbstractLayerUI<JTextComponent> {

    private final ScheduledExecutorService executor;
    
    private ScheduledFuture<?> future;
    
    private Map<Integer, List<Character>> characterMap;
    
    public UnicodeTextLayerUI() {
        executor = Executors.newSingleThreadScheduledExecutor();
        characterMap = new HashMap<Integer, List<Character>>();
        
        characterMap.put(KeyEvent.VK_E, new ArrayList<Character>());
        characterMap.get(KeyEvent.VK_E).add('\u00E8');
        characterMap.get(KeyEvent.VK_E).add('\u00E9');
        characterMap.get(KeyEvent.VK_E).add('\u00EA');
        characterMap.get(KeyEvent.VK_E).add('\u00EB');
    }
    
    @Override
    protected void paintLayer(Graphics2D arg0, JXLayer<JTextComponent> arg1) {
        super.paintLayer(arg0, arg1);
    }
    
    private void showUnicodeInput(KeyEvent e, JTextComponent c) {
        System.out.println("Show unicode input.." + e.getKeyCode());
        List<Character> charMap = characterMap.get(e.getKeyCode());
        if (charMap != null) {
            /*
            JPanel charMapPanel = new JPanel();
            for (Character character : charMap) {
                charMapPanel.add(new JButton(String.valueOf(character)));
            }
            JOptionPane.showInputDialog(c, charMapPanel, "Character Map", JOptionPane.QUESTION_MESSAGE);
            */
            Object selection = JOptionPane.showInputDialog(c, "Select Character", "Character Map",
                    JOptionPane.QUESTION_MESSAGE, null, charMap.toArray(), charMap.toArray()[0]);
            if (selection != null) {
                c.setText(c.getText().substring(0, c.getText().length() - 1) + selection);
                c.setCaretPosition(c.getText().length());
            }
        }
    }
    
    @Override
    protected void processKeyEvent(KeyEvent e, JXLayer<JTextComponent> layer) {
        super.processKeyEvent(e, layer);
        if (e.getID() == KeyEvent.KEY_PRESSED && future == null) {
            future = executor.schedule(new ShowUnicodeInputCommand(e, layer.getView()), 200, TimeUnit.MILLISECONDS);            
        }
        else if (e.getID() == KeyEvent.KEY_RELEASED && future != null) {
            future.cancel(false);
            future = null;
        }
    }
    
    private class ShowUnicodeInputCommand implements Runnable {
        
        private final KeyEvent e;
        
        private final JTextComponent c;
        
        private ShowUnicodeInputCommand(KeyEvent e, JTextComponent c) {
            this.e = e;
            this.c = c;
        }
        
        @Override
        public void run() {
            showUnicodeInput(e, c);
        }
    }
}
