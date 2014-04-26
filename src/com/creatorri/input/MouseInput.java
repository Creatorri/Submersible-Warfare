package com.creatorri.input;

import com.creatorri.LD29;
import static com.creatorri.LD29.SCALE;
import static com.creatorri.LD29.height;
import static com.creatorri.LD29.level;
import static com.creatorri.LD29.width;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Torri
 */
public class MouseInput implements MouseListener {

    public int mx;
    public int my;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX() - 10;
        my = e.getY() - 30;
        double firingAngle;
        firingAngle = Math.atan2(mx - level.entities.get(0).x * 64, my - level.entities.get(0).y * 64);
        firingAngle -= Math.PI / 2.0;
        firingAngle = Math.toDegrees(firingAngle);
        if (firingAngle < 0) {
            firingAngle += 360;
        }
        LD29.level.entities.get(0).fire(firingAngle);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
