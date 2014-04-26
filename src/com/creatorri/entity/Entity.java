/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity;

import com.creatorri.assets.LoadArt;
import com.creatorri.level.Level;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public BufferedImage img;
    protected final LoadArt la = new LoadArt();
    protected final Level level;
    protected static final Random rand = new Random();

    public Entity(Level l) {
        level = l;
        level.entities.add(this);
        ID = level.entities.size() + 4;
        x = rand.nextInt(level.WIDTH);
        y = rand.nextInt(level.HEIGHT);
        while (level.getDataAt(x, y) != 0) {
            x = rand.nextInt(level.WIDTH);
            y = rand.nextInt(level.HEIGHT);
        }
        level.setDataAt(x, y, ID);
        img = la.createBufferedImage("Submarine.png", 16, 16);
    }

    public void move(int dx, int dy) {
        if (level.getDataAt(dx, dy) != 0) {
            return;
        }
        level.setDataAt(x, y, 0);
        x += dx;
        y += dy;
        level.setDataAt(x, y, ID);
    }

    public abstract void tick();
}
