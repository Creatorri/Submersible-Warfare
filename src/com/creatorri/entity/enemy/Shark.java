/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity.enemy;

import com.creatorri.LD29;
import com.creatorri.entity.Entity;
import com.creatorri.level.Level;

/**
 *
 * @author Creatorri
 */
public class Shark extends Entity {

    int tick = 0;

    public Shark(Level o) {
        super(o);
        img = la.createBufferedImage("Shark.png", LD29.SCALE, LD29.SCALE);
        health = 10;
    }

    @Override
    public void tick() {
        tick++;
        if (tick % 5 == 0) {
            if (distTo(level.entities.get(0)) < 3) {
                move(level.entities.get(0).x - x > 0 ? 1 : -1, level.entities.get(0).y - y > 0 ? 1 : -1);
            } else {
                move(rand.nextInt() % 2, rand.nextInt() % 2);
            }
        }
        if(health<=0){
            death();
        }
    }
}
