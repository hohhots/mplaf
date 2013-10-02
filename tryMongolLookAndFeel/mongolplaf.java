/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tryMongolLookAndFeel;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author brgd
 */
public class mongolplaf {

    public mongolplaf() {
    }

    private static void initLookAndFeel() {
        try {
            // Set mongol Java L&F (also called "Mongol")
            UIManager.setLookAndFeel(
                    "com.nm114.plaf.mongol.MongolLookAndFeel");
            //UIManager.getCrossPlatformLookAndFeelClassName());

            //Make sure we have nice window decorations.
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.print("UnsupportedLookAndFeelException");
        // handle exception
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException");
        // handle exception
        } catch (InstantiationException e) {
            System.out.print("InstantiationException");
        // handle exception
        } catch (IllegalAccessException e) {
            System.out.print("IllegalAccessException");
        // handle exception
        }
    }

    private static void createAndShowGUI() {
        //Set the look and feel.
        initLookAndFeel();

        //Create and set up the window.
        JFrame frame = new JFrame("Mongol plaf -- nm114");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();

        JLabel jl = new JLabel("My Label");
        jl.setOpaque(true);
        jl.setBackground(Color.RED);
        JButton jb = new JButton("dddfghdfgh d");
        JButton jb1 = new JButton("hhyerty h");

        Container cp = frame.getContentPane();
        cp.add(jp, BorderLayout.CENTER);

        jp.add(jl);
        jp.add(jb);
        jp.add(jb1);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }
}
