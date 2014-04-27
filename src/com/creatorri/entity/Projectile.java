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
public class Projectile extends Entity {

    int xVel, yVel;
    int life;

    public Projectile(Level l, int x, int y, int vel, double angle) {
        super(l);
        img = la.createBufferedImage("Torpedo.png", LD29.SCALE / 4, LD29.SCALE / 4);
        if (angle < 90) {
            xVel = (int) (Math.sin(Math.toRadians(angle)) * vel);
            yVel = (int) (Math.cos(Math.toRadians(angle)) * vel);
        } else if (angle == 90) {
            xVel = vel;
            yVel = 0;
        } else if (angle > 90 && angle < 180) {
            xVel = (int) (Math.cos(Math.toRadians(angle)) * vel);
            yVel = (int) (Math.sin(Math.toRadians(angle)) * vel);
        } else if (angle == 180) {
            xVel = 0;
            yVel = vel;
        } else if (angle > 180 && angle < 270) {
            xVel = -(int) (Math.cos(Math.toRadians(angle)) * vel);
            yVel = -(int) (Math.sin(Math.toRadians(angle)) * vel);
        } else if (angle == 270) {
            xVel = -vel;
            yVel = 0;
        } else if (angle > 270) {
            xVel = -(int) (Math.sin(Math.toRadians(angle)) * vel);
            yVel = (int) (Math.cos(Math.toRadians(angle)) * vel);
        }
        if (xVel > 0) {
            moveTo(x + 1, y);
        }
        if (xVel < 0) {
            moveTo(x - 1, y);
        }
        if (yVel > 0) {
            moveTo(x, y + 1);
        }
        if (yVel < 0) {
            moveTo(x, y - 1);
        }
        mele = 50;
    }

    @Override
    public void mele(Entity e) {
        super.mele(e);
        System.out.println("PROJECTILE ATTACKS " + e.getClass().getSimpleName());
        death();
    }

    @Override
    public void tick() {
        life++;
        if (life > 50) {
            death();
        }
        move(xVel, yVel);
    }
}
