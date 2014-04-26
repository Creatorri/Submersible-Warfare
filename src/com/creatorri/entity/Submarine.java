/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity;

import com.creatorri.LD29;
import com.creatorri.level.Level;

/**
 *
 * @author Creatorri
 */
public class Submarine extends Entity {

    public int score = 0;

    public Submarine(Level o) {
        super(o);
        mele = 100;
    }

    @Override
    public void tick() {
        int dx = 0, dy = 0;
        if (LD29.ki.keys[87]) {
            dy++;
        }
        if (LD29.ki.keys[83]) {
            dy--;
        }
        if (LD29.ki.keys[65]) {
            dx--;
        }
        if (LD29.ki.keys[68]) {
            dx++;
        }
        move(dx, dy);
    }
}
