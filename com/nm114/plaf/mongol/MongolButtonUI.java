/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm114.plaf.mongol;

import sun.swing.SwingUtilities2;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.geom.*;
import java.beans.*;
import javax.swing.plaf.*;

import com.nm114.plaf.*;

/**
 * MongolButtonUI implementation
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans<sup><font size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @version 1.39 11/30/05
 * @author Tom Santos
 */
public class MongolButtonUI extends BasicButtonUI {

    private AffineTransform origAt;
    private final static MongolButtonUI mongolButtonUI = new MongolButtonUI();    // NOTE: These are not really needed, but at this point we can't pull
    // them. Their values are updated purely for historical reasons.
    protected Color focusColor;
    protected Color selectColor;
    protected Color disabledTextColor;

    // ********************************
    //          Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c) {
        return mongolButtonUI;
    }
    // ********************************
    //          Install
    // ********************************
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
    }

    public void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
    }

    /*// ********************************
    //          Layout Methods
    // ********************************
    public Dimension getMinimumSize(JComponent c) {
    Dimension d = getPreferredSize(c);
    View v = (View) c.getClientProperty(BasicHTML.propertyKey);
    if (v != null) {
    d.height -= v.getPreferredSpan(View.X_AXIS) - v.getMinimumSpan(View.X_AXIS);
    }
    return d;
    }
     */
    public Dimension getPreferredSize(JComponent c) {
        Dimension dm = super.getPreferredSize(c);
        Dimension mdm = new Dimension(dm.height, dm.width);
        return mdm;
    }

    /*
    public Dimension getMaximumSize(JComponent c) {
    Dimension d = getPreferredSize(c);
    View v = (View) c.getClientProperty(BasicHTML.propertyKey);
    if (v != null) {
    d.height += v.getMaximumSpan(View.X_AXIS) - v.getPreferredSpan(View.X_AXIS);
    }
    return d;
    }*/    // ********************************
    //         Create Listeners
    // ********************************
    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return super.createButtonListener(b);
    }
    // ********************************
    //         Default Accessors 
    // ********************************
    protected Color getSelectColor() {
        selectColor = UIManager.getColor(getPropertyPrefix() + "select");
        return selectColor;
    }

    protected Color getDisabledTextColor() {
        disabledTextColor = UIManager.getColor(getPropertyPrefix() +
                "disabledText");
        return disabledTextColor;
    }

    protected Color getFocusColor() {
        focusColor = UIManager.getColor(getPropertyPrefix() + "focus");
        return focusColor;
    }

    // ********************************
    //          Paint
    // ********************************
    /**
     * If necessary paints the background of the component, then
     * invokes <code>paint</code>.
     *
     * @param g Graphics to paint to
     * @param c JComponent painting on
     * @throws NullPointerException if <code>g</code> or <code>c</code> is
     *         null
     * @see javax.swing.plaf.ComponentUI#update
     * @see javax.swing.plaf.ComponentUI#paint
     * @since 1.5
     */
    public void update(Graphics g, JComponent c) {

        AbstractButton button = (AbstractButton) c;
        if ((c.getBackground() instanceof UIResource) &&
                button.isContentAreaFilled() && c.isEnabled()) {
            ButtonModel model = button.getModel();
            if (!MongolUtils.isToolBarButton(c)) {
                if (!model.isArmed() && !model.isPressed()) {
                    paint(g, c);
                    return;
                }
            } else if (model.isRollover() && MongolUtils.drawGradient(
                    c, g, "Button.gradient", 0, 0, c.getWidth(),
                    c.getHeight(), false)) {
                paint(g, c);
                return;
            }
        }
        super.update(g, c);
    }
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();

    public void paint(Graphics g, JComponent c) {
        setVertical(g, c);

        g.setColor(MongolLookAndFeel.getWhite());
        g.fillRect(0, 0, c.getHeight(), c.getWidth());
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        String text = layout(b, SwingUtilities2.getFontMetrics(b, g),
                b.getHeight(), b.getWidth());

        clearTextShiftOffset();

        // perform UI specific press action, e.g. Windows L&F shifts text
        if (model.isArmed() && model.isPressed()) {
            paintButtonPressed(g, b);
        }

        // Paint the Icon
        if (b.getIcon() != null) {
            paintIcon(g, c, iconRect);
        }

        if (text != null && !text.equals("")) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                v.paint(g, textRect);
            } else {
                paintText(g, b, textRect, text);
            }
        }

        if (b.isFocusPainted() && b.hasFocus()) {
            // paint UI specific focus
            paintFocus(g, b, viewRect, textRect, iconRect);
        }

        ((Graphics2D) g).setTransform(origAt); //nm114
    }

    private String layout(AbstractButton b, FontMetrics fm,
            int width, int height) {
        Insets i = b.getInsets();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        // layout the text and icon
        return SwingUtilities.layoutCompoundLabel(
                b, fm, b.getText(), b.getIcon(),
                b.getVerticalAlignment(), b.getHorizontalAlignment(),
                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
                viewRect, iconRect, textRect,
                b.getText() == null ? 0 : b.getIconTextGap());
    }

    protected void paintFocus(Graphics g, AbstractButton b,
            Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {

        Rectangle focusRect = b.getBounds();

        g.setColor(getFocusColor());
        g.drawRect(4, 5,
                focusRect.height - 9, focusRect.width - 9);
    }

    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {

        g.setColor(MongolLookAndFeel.getControl());
        g.fillRect(0, 0, c.getHeight(), c.getWidth());
        
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemIndex = b.getDisplayedMnemonicIndex();

        // Draw the Text 
        if (model.isEnabled()) {
            // paint the text normally
            g.setColor(b.getForeground());
        } else {
            // paint the text disabled 
            g.setColor(getDisabledTextColor());
        }

        SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemIndex,
                textRect.x, textRect.y + fm.getAscent());

    }

    private void setVertical(Graphics g, JComponent c) {

        Graphics2D g2 = (Graphics2D) g; //nm114
        origAt = g2.getTransform();//nm114

        AffineTransform at = new AffineTransform();//nm114
        at.translate(c.getWidth(), 0);//nm114
        at.rotate(Math.PI / 2.0);//nm114
        g2.transform(at);//nm114 */
    }
}
