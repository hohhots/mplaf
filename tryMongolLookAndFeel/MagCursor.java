/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tryMongolLookAndFeel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.image.*;

public class MagCursor {

    /*
    steps for this new hack

    create bitmap w/ mag glass on it
    set custom cursor
    do overlay drawing of dimensions
    restrict drawing based on cursor location (clip rect)

    */



    public static void main(String[] args) {
        BufferedImage img = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics g = img.getGraphics();
        g.setColor(Color.blue);
        g.drawRect(0,0,31,31);
        g.drawRect(15,15,2,2);
        Point hotspot = new Point((int)(16),(int)(16));
        Toolkit tk = Toolkit.getDefaultToolkit();
        Cursor cursor = tk.createCustomCursor(img,hotspot,"JoshyMag");


        JFrame frame = new JFrame("frame");
        frame.getContentPane().add("North",new JButton("button"));
        frame.getContentPane().add("South",new JTextField("textfield"));
        frame.getContentPane().add("Center",new JTextArea(3,20));
        frame.getContentPane().add("East",new JLabel("label"));
        frame.getContentPane().add("West",new JPasswordField("password"));
        frame.pack();
        frame.show();

        LabelGlassPane glass = new LabelGlassPane(frame);
        glass.setCursor(cursor);
        frame.setGlassPane(glass);
        glass.setVisible(true);
    }

}


class LabelGlassPane extends JComponent {
    public JFrame frame;
    public int x, y;
    public LabelGlassPane(JFrame frame) {
        this.frame = frame;
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                x = evt.getX();
                y = evt.getY();
                LabelGlassPane.this.repaint();
            }
        });
    }
    public void paint(Graphics g) {
        g.setColor(Color.red);
        Container root = frame.getContentPane();
        Rectangle clip = g.getClipBounds();
        g.setClip(this.x-16,this.y-16,32,32);
        rPaint(root,g);
        g.setClip(clip);
    }
    private void rPaint(Container cont, Graphics g) {
        for(int i=0; i<cont.getComponentCount(); i++) {
            Component comp = cont.getComponent(i);
            if(!(comp instanceof JPanel)) {
                int x = comp.getX();
                int y = comp.getY();
                int w = comp.getWidth();
                int h = comp.getHeight();
                g.setColor(new Color(100,100,100,100));
                g.drawRect(x+4,y+4,w-8,h-8);
                g.drawString(comp.getClass().getName(),x+10,y+20);
                g.setColor(new Color(255,0,0,100));
                g.drawString(this.x + "," + this.y, this.x-16,this.y-5);
            }
            if(comp instanceof Container) {
                rPaint((Container)comp,g);
            }
        }
    }
}
