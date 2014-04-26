package com.creatorri.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This gets and keeps information from the keyboard
 *
 * @author Torri
 */
public class KeyboardInput implements KeyListener {

    public boolean[] keys = new boolean[1000];
    public boolean turn = true;
    public boolean pause = false;
    public boolean connected = false;

    /**
     * Receives when key is typed
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Receives when key is pressed
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    /**
     * Receives when key is released
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
