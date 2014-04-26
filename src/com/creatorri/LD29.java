/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * THEME: Beneath the Surface IDEAS: Submarine warfare - 2D side-scrolling
 * upgrade based
 *
 * @author Creatorri
 */
public class LD29 extends JFrame {

    public LD29() {
        super("Submersible Warfare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
}
