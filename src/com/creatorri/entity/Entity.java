/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.entity;

import com.creatorri.LD29;
import com.creatorri.assets.LoadArt;
import com.creatorri.level.Level;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Creatorri
 */
public abstract class Entity {

    public int x = 0, y = 0;
    public int ID;
    public double health = 100;
    public double mele = 10;
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
        img = la.createBufferedImage("Submarine.png", LD29.SCALE, LD29.SCALE);
    }

    public void move(int dx, int dy) {
        for(int xx=x;xx<dx+x; xx += dx>0 ? 1 : -1){
            for(int yy=y;yy<dy+y; yy += dy>0 ? 1 : -1){
                if(level.getDataAt(xx, yy)>4){
                    mele(level.entities.get(level.getDataAt(xx, yy)-4));
                }
            }
        }
        if (level.getDataAt(dx + x, dy + y) != 0) {
            return;
        }
        level.setDataAt(x, y, 0);
        x += dx;
        y += dy;
        level.setDataAt(x, y, ID);
        if (health <= 0) {
            death();
        }
    }

    public void moveTo(int x, int y) {
        if (level.getDataAt(x, y) != 0) {
            return;
        }
        level.setDataAt(this.x, this.y, 0);
        this.x = x;
        this.y = y;
        level.setDataAt(x, y, ID);
    }

    public double distTo(Entity e) {
        return Math.sqrt(Math.pow(x - e.x, 2) + Math.pow(y - e.y, 2));
    }

    public void fire(double angle) {
        level.entities.add(new Projectile(level, x + 1, y + 1, 2, angle));
    }

    public void death() {
        level.entities.remove(this);
        level.entities.stream().forEach((e) -> {
            if (e.ID > ID) {
                e.ID--;
                level.setDataAt(e.x, e.y, e.ID);
            }
        });
        level.setDataAt(x, y, 0);
    }
    
    public void mele(Entity e){
        e.health-=mele;
    }

    public abstract void tick();
}
