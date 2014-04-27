/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri;

import com.creatorri.assets.LoadArt;
import com.creatorri.entity.Projectile;
import com.creatorri.input.KeyboardInput;
import com.creatorri.input.MouseInput;
import com.creatorri.level.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * THEME: Beneath the Surface IDEAS: Submarine warfare - 2D side-scrolling
 * upgrade based
 *
 * @author Creatorri
 */
public class LD29 extends JFrame implements Runnable {

    public static boolean running = true;
    public static final int SCALE = 64;
    public static Level level;
    public Thread t;
    private static final LoadArt la = new LoadArt();
    public static final BufferedImage WATER = la.createBufferedImage("Water.png", SCALE, SCALE),
            ROCK = la.createBufferedImage("Rock.png", SCALE, SCALE),
            SAND = la.createBufferedImage("Sand.png", SCALE, SCALE),
            CORAL = la.createBufferedImage("Coral.png", SCALE, SCALE),
            AIR = la.createBufferedImage("Air.png", SCALE, SCALE);

    public static KeyboardInput ki;
    public static MouseInput mi;
    public static int fps;
    public static int width, height;
    public static LD29 ld;

    public LD29() {
        super("Submersible Warfare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        ki = new KeyboardInput();
        addKeyListener(ki);
        mi = new MouseInput();
        addMouseListener(mi);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.err);
        }
        startGame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ld = new LD29();
    }

    public static void newLevel() {
        level = new Level(255, 60);
    }

    public void startGame() {
        t = new Thread(this, "GAME");
        t.start();
    }

    public void render() {

        if (this == null) {
            return;
        }

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        width = getWidth();
        height = getHeight();

        Graphics g = bs.getDrawGraphics();
        int offx = width / (2 * SCALE) - (int) (level.entities.get(0).x + SCALE / 90);
        int offy = height / (2 * SCALE) - (level.entities.get(0).y - 2);
        for (int x = -1; x < (width + SCALE) / SCALE; x++) {
            for (int y = -1; y < (height + SCALE) / SCALE; y++) {
                g.drawImage(WATER, x * SCALE, height - y * SCALE, this);
                if (level.getDataAt(x - offx, y - offy) == Level.WATER) {
                    g.drawImage(WATER, x * SCALE, height - (y * SCALE), this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.ROCK) {
                    g.drawImage(ROCK, x * SCALE, height - y * SCALE, this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.CORAL) {
                    g.drawImage(CORAL, x * SCALE, height - y * SCALE, this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.SAND) {
                    g.drawImage(SAND, x * SCALE, height - y * SCALE, this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.AIR) {
                    g.drawImage(AIR, x * SCALE, height - y * SCALE, this);
                } else if (level.getDataAt(x - offx, y - offy) > 4) {
                    if (level.getDataAt(x - offx, y - offy) - 4 >= level.entities.size() || level.getDataAt(x - offx, y - offy) - 4 < 0) {
                        level.setDataAt(x - offx, y - offy, 0);
                        continue;
                    }
                    if (level.entities.get(level.getDataAt(x - offx, y - offy) - 4).health <= 0) {
                        level.entities.get(level.getDataAt(x - offx, y - offy) - 4).death();
                        continue;
                    }
                    if(!(level.entities.get(level.getDataAt(x - offx, y - offy) - 4) instanceof Projectile)){
                        g.setColor(Color.red);
                        g.fillRect(x * SCALE, height - y * SCALE, (int) (SCALE * level.entities.get(level.getDataAt(x - offx, y - offy) - 4).health / level.entities.get(level.getDataAt(x - offx, y - offy) - 4).maxhealth), 10);
                    }
                    g.drawImage(level.entities.get(level.getDataAt(x - offx, y - offy) - 4).img, x * SCALE, height - y * SCALE, this);
                }
            }
        }
        g.dispose();
        bs.show();
    }

    public void tick() {
        try {
            level.entities.stream().forEach((e) -> {
                if (e != null) {
                    e.tick();
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        if (level == null) {
            newLevel();
        }
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1.0 / 12.0;
        int tickCount = 0;
        boolean ticked = true;
        while (running) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;
            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;
                if (tickCount % 60 == 0) {
                    fps = frames;
                    previousTime += 1000;
                    frames = 0;
                }
            }
            if (ticked) {
                render();
                frames++;
                ticked = false;
            }
            render();
            frames++;
        }
        System.gc();
    }
}
