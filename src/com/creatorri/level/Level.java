/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri.level;

import com.creatorri.entity.Entity;
import com.creatorri.entity.Submarine;
import com.creatorri.entity.enemy.Shark;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Creatorri
 */
public class Level {

    /**
     * 0 is water 1 is rock 2 is sand 3 is coral 4 is air >4 entity ID
     */
    private int[] level;
    public final int WIDTH, HEIGHT;
    public static final int WATER = 0, ROCK = 1, SAND = 2, CORAL = 3, AIR = 4;
    public ArrayList<Entity> entities = new ArrayList<>();
    private final Random rand = new Random();

    public Level(int width, int height) {
        level = new int[width * height];
        WIDTH = width;
        HEIGHT = height;
        for (int xx = 0; xx < width; xx++) {
            level[xx] = ROCK;
            level[xx + width] = ROCK;
            level[xx + 2 * width] = rand.nextInt() % 6 == 0 ? ROCK : WATER;
            int y = level[xx + 2 * width] == WATER ? 2 : 3;
            level[xx + y * width] = SAND;
            level[xx + (y + 1) * width] = SAND;
            level[xx + (y + 2) * width] = rand.nextInt() % 30 == 0 ? CORAL : 0;
            level[xx + (height - 1) * width] = AIR;
            level[xx + (height - 2) * width] = AIR;
            level[xx + (height - 3) * width] = AIR;
        }

        entities.add(new Submarine(this));
        for (int i = 0; i < 20; i++) {
            entities.add(new Shark(this));
        }
    }

    public int getDataAt(int x, int y) {
        if (y > HEIGHT - 3) {
            return AIR;
        }
        if (y < 0) {
            return ROCK;
        }
        if ((x > WIDTH || x < 0) && y < HEIGHT - 3) {
            return WATER;
        }
        if ((x > WIDTH || x < 0) && y > HEIGHT - 3) {
            return AIR;
        }
        if ((x > WIDTH || x < 0) && y > 0) {
            return ROCK;
        }

        return level[x + y * WIDTH];
    }

    public void setDataAt(int x, int y, int data) {
        level[x + y * WIDTH] = data;
    }
}
