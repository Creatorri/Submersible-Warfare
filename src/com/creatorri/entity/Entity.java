/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity;

import com.creatorri.level.Level;
import java.util.Random;

/**
 *
 * @author Creatorri
 */
public abstract class Entity {

    public int x = 0, y = 0;
    public final int ID;
    public double xVel, yVel;
    public double health;
    protected final Level level;
    protected final Random rand = new Random();

    public Entity(Level l) {
        level = l;
        level.entities.add(this);
        ID = level.entities.size() - 1;
        x = rand.nextInt(level.WIDTH);
        y = rand.nextInt(level.HEIGHT);
        while (level.level[x + y * level.WIDTH] != 0) {
            x = rand.nextInt(level.WIDTH);
            y = rand.nextInt(level.HEIGHT);
        }
    }

    public abstract void tick();
}
