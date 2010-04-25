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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * @author Ben
 *
 */
public class JTextFieldExt extends JTextField {

    private static final long serialVersionUID = 7055233454947235347L;

    private String promptText;
    
    private int promptFontStyle;
    
    private Color promptColour;
    
    private Font defaultFont = getFont();
    
    private Color defaultColour = getForeground();
    
    /**
     * 
     */
    public JTextFieldExt() {
        addFocusListener(new FocusListenerImpl());
        addKeyListener(new EscapeKeyListener());
    }

    /**
     * @param text
     */
    public JTextFieldExt(String text) {
        super(text);
        addFocusListener(new FocusListenerImpl());
        addKeyListener(new EscapeKeyListener());
    }

    /**
     * @param columns
     */
    public JTextFieldExt(int columns) {
        super(columns);
        addFocusListener(new FocusListenerImpl());
        addKeyListener(new EscapeKeyListener());
    }

    /**
     * @param text
     * @param columns
     */
    public JTextFieldExt(String text, int columns) {
        super(text, columns);
        addFocusListener(new FocusListenerImpl());
        addKeyListener(new EscapeKeyListener());
    }

    /**
     * @param doc
     * @param text
     * @param columns
     */
    public JTextFieldExt(Document doc, String text, int columns) {
        super(doc, text, columns);
        addFocusListener(new FocusListenerImpl());
        addKeyListener(new EscapeKeyListener());
    }

    /**
     * @return the promptText
     */
    public String getPromptText() {
        return promptText;
    }

    /**
     * @param promptText the promptText to set
     */
    public void setPromptText(String promptText) {
        this.promptText = promptText;
        super.setText(promptText);
    }

    /**
     * @return the promptFontStyle
     */
    public int getPromptFontStyle() {
        return promptFontStyle;
    }

    /**
     * @param promptFontStyle the promptFontStyle to set
     */
    public void setPromptFontStyle(int promptFontStyle) {
        this.promptFontStyle = promptFontStyle;
        super.setFont(getFont().deriveFont(promptFontStyle));
    }

    /**
     * @return the promptColour
     */
    public Color getPromptColour() {
        return promptColour;
    }

    /**
     * @param promptColour the promptColour to set
     */
    public void setPromptColour(Color promptColour) {
        this.promptColour = promptColour;
        super.setForeground(promptColour);
    }

    @Override
    public void setText(String t) {
        if (t == null || t.isEmpty()) {
            super.setText(promptText);
            setCaretPosition(0);
            if (promptFontStyle >= 0) {
                super.setFont(getFont().deriveFont(promptFontStyle));
            }
            if (promptColour != null) {
                super.setForeground(promptColour);
            }
        }
        else {
            super.setText(t);
        }
    }
    
    @Override
    public String getText() {
        if (promptText != null && promptText.equals(super.getText())) {
            return null;
        }
        return super.getText();
    }
    
    @Override
    public void setFont(Font f) {
        super.setFont(f);
        defaultFont = f;
    }
    
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        defaultColour = fg;
    }
    
    private class FocusListenerImpl implements FocusListener {
        
        @Override
        public void focusGained(FocusEvent e) {
            if (promptText != null && promptText.equals(JTextFieldExt.super.getText())) {
                JTextFieldExt.super.setText(null);
            }
            setFont(defaultFont);
            setForeground(defaultColour);
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            if (JTextFieldExt.super.getText() == null || JTextFieldExt.super.getText().isEmpty()) {
                JTextFieldExt.super.setText(promptText);
                setCaretPosition(0);
                if (promptFontStyle >= 0) {
                    JTextFieldExt.super.setFont(getFont().deriveFont(promptFontStyle));
                }
                if (promptColour != null) {
                    JTextFieldExt.super.setForeground(promptColour);
                }
            }
        }
    }
    
    private class EscapeKeyListener extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                JTextFieldExt.super.setText(null);
            }
        }
    }
}
