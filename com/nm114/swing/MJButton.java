/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm114.swing;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author brgd
 */
public class MJButton extends JButton{

    
    public MJButton(String name) {
         super(name);
    }

    public void paint(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g; //nm114 //888888888888888888888888888888888888888888
        //AffineTransform origat = g2.getTransform();//nm114
        //AffineTransform at = new AffineTransform();//nm114
        //int w =super.getWidth();
        //int h = super.getWidth();
        //at.translate(28, 0);
        //at.rotate(Math.PI / 2.0);//nm114
        //g2.transform(at);//nm114 */
        //
        Rectangle origb = g.getClipBounds();
        //System.out.println(origb.width);
       
        super.paint(g);
        
        //g2.setTransform(origat); //nm114
    }
}
