/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm114.plaf.mongol;

import sun.swing.SwingUtilities2;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.text.View;


import java.awt.*;
import java.awt.geom.*;

/**
 * A Windows L&F implementation of LabelUI.  This implementation 
 * is completely static, i.e. there's only one UIView implementation 
 * that's shared by all JLabel objects.
 *
 * @version 1.15 11/30/05
 * @author Hans Muller
 */
public class MongolLabelUI extends BasicLabelUI {

    private AffineTransform origAt;
    /**
     * The default <code>MongolLabelUI</code> instance. This field might
     * not be used. To change the default instance use a subclass which
     * overrides the <code>createUI</code> method, and place that class
     * name in defaults table under the key "LabelUI".
     */
    protected static MongolLabelUI mongolLabelUI = new MongolLabelUI();
    private final static MongolLabelUI SAFE_MONGOL_LABEL_UI = new MongolLabelUI();

    public static ComponentUI createUI(JComponent c) {
        if (System.getSecurityManager() != null) {
            return SAFE_MONGOL_LABEL_UI;
        } else {
            return mongolLabelUI;
        }
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
    private static Rectangle paintIconR = new Rectangle();
    private static Rectangle paintTextR = new Rectangle();
    private static Rectangle paintViewR = new Rectangle();
    private static Insets paintViewInsets = new Insets(0, 0, 0, 0);

    public void paint(Graphics g, JComponent c) {
        setVertical(g, c);
        
        JLabel label = (JLabel) c;
        String text = label.getText();
        Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();

        if ((icon == null) && (text == null)) {
            return;
        }

        FontMetrics fm = SwingUtilities2.getFontMetrics(label, g);
        String clippedText = layout(label, fm, c.getHeight(), c.getWidth());

        if (icon != null) {
            icon.paintIcon(c, g, paintIconR.x, paintIconR.y);
        }

        if (text != null) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                v.paint(g, paintTextR);
            } else {
                int textX = paintTextR.x;
                int textY = paintTextR.y + fm.getAscent();

                if (label.isEnabled()) {
                    paintEnabledText(label, g, clippedText, textX, textY);
                } else {
                    paintDisabledText(label, g, clippedText, textX, textY);
                }
            }
        }
        
        ((Graphics2D)g).setTransform(origAt); //nm114
    }

    protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY) {
        int mnemIndex = l.getDisplayedMnemonicIndex();
        g.setColor(l.getForeground());
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
                textX, textY);
    }

    /**
     * Just paint the text gray (Label.disabledForeground) rather than 
     * in the labels foreground color.
     *
     * @see #paint
     * @see #paintEnabledText
     */
    protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY) {
        
        int mnemIndex = l.getDisplayedMnemonicIndex();
        g.setColor(UIManager.getColor("Label.disabledForeground"));
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
                textX, textY);
    }

    private String layout(JLabel label, FontMetrics fm,
            int width, int height) {
        Insets insets = label.getInsets(paintViewInsets);
        String text = label.getText();
        Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();
        paintViewR.x = insets.left;
        paintViewR.y = insets.top;
        paintViewR.width = width - (insets.left + insets.right);
        paintViewR.height = height - (insets.top + insets.bottom);
        paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
        paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;
        return layoutCL(label, fm, text, icon, paintViewR, paintIconR,
                paintTextR);
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
