/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity.enemy;

import com.creatorri.entity.Entity;
import com.creatorri.level.Level;

/**
 *
 * @author Creatorri
 */
public class Shark extends Entity {

    public Shark(Level o) {
        super(o);
        img = la.createBufferedImage("Shark", 16, 16);
    }

    @Override
    public void tick() {
    }
}
