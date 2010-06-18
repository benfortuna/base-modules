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
package org.mnode.base.desktop.text;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * @author Ben
 * 
 */
public class NumberedEditorKit extends StyledEditorKit {

    private static final long serialVersionUID = 1L;

    public ViewFactory getViewFactory() {
        return new NumberedViewFactory();
    }

    private static class NumberedViewFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null)
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    // return new ParagraphView(elem);
                    return new NumberedParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new NoWrapBoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            // default to text display
            return new LabelView(elem);
        }
    }

    private static class NoWrapBoxView extends BoxView {
        public NoWrapBoxView(Element elem, int axis) {
            super(elem, axis);
        }

        public void layout(int width, int height) {
            super.layout(32768, height);
        }

        public float getMinimumSpan(int axis) {
            return super.getPreferredSpan(axis);
        }
    }

    private static class NumberedParagraphView extends ParagraphView {
        
        public static short NUMBERS_WIDTH = 25;

        public NumberedParagraphView(Element e) {
            super(e);
            short top = 0;
            short left = 0;
            short bottom = 0;
            short right = 0;
            this.setInsets(top, left, bottom, right);
        }

        protected void setInsets(short top, short left, short bottom, short right) {
            super.setInsets(top, (short) (left + NUMBERS_WIDTH), bottom, right);
        }

        public void paintChild(Graphics g, Rectangle r, int n) {
            super.paintChild(g, r, n);
            int previousLineCount = getPreviousLineCount();
            int numberX = r.x - getLeftInset();
            int numberY = r.y + r.height - 5;
            g.setColor(Color.LIGHT_GRAY);
            g.drawString(Integer.toString(previousLineCount + n + 1), numberX, numberY);
        }

        public int getPreviousLineCount() {
            int lineCount = 0;
            View parent = this.getParent();
            int count = parent.getViewCount();
            for (int i = 0; i < count; i++) {
                if (parent.getView(i) == this) {
                    break;
                } else {
                    lineCount += parent.getView(i).getViewCount();
                }
            }
            return lineCount;
        }
    }

}
