/*
 * All code and art written by Creatorri within the 48 hour time limit
 */
package com.creatorri;

import com.creatorri.assets.LoadArt;
import com.creatorri.level.Level;
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
    private static Level level;
    public Thread t;
    private static final LoadArt la = new LoadArt();
    public static final BufferedImage WATER = la.createBufferedImage("Water.png", 64, 64),
            ROCK = la.createBufferedImage("Rock.png", 64, 64),
            SAND = la.createBufferedImage("Sand.png", 64, 64),
            CORAL = la.createBufferedImage("Coral.png", 64, 64),
            AIR = la.createBufferedImage("Air.png", 64, 64);

    public LD29() {
        super("Submersible Warfare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
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
        new LD29();
    }

    public static void newLevel() {
        level = new Level(100, 60);
    }

    public static Level getLevel() {
        return level;
    }

    public void startGame() {
        t = new Thread(this, "GAME");
        t.start();
    }

    public void render() {

        if (this == null) {
            return;
        }

        this.setFocusable(true);

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        int offx = (level.entities.get(0).x / 2);
        int offy = (level.entities.get(0).y / 2);
        for (int x = -1; x < (getWidth() / 64) + 1; x++) {
            for (int y = -1; y < (getHeight() / 64) + 1; y++) {
                g.drawImage(WATER, x * 64, getHeight() - (y * 64), this);
                if (level.getDataAt(x - offx, y - offy) == Level.WATER) {
                    g.drawImage(WATER, x * 64, getHeight() - (y * 64), this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.ROCK) {
                    g.drawImage(ROCK, x * 64, getHeight() - (y * 64), this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.CORAL) {
                    g.drawImage(CORAL, x * 64, getHeight() - (y * 64), this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.SAND) {
                    g.drawImage(SAND, x * 64, getHeight() - (y * 64), this);
                } else if (level.getDataAt(x - offx, y - offy) == Level.AIR) {
                    g.drawImage(AIR, x * 64, getHeight() - (y * 64), this);
                } else {
                    g.drawImage(level.entities.get(level.getDataAt(x - offx, y - offy) - 4).img, x * 64, getHeight() - (y * 64), this);
                }
            }
        }
        g.dispose();
        bs.show();
    }

    public void tick() {
        level.entities.stream().forEach((e) -> {
            e.tick();
        });
    }

    @Override
    public void run() {
        if (level == null) {
            newLevel();
        }
        while (running) {
            render();
        }
        System.gc();
    }
}
