package com.tienbi.marioproject.map;

import com.tienbi.marioproject.gui.GUI;
import com.tienbi.marioproject.manager.ImageLoader;

import java.awt.*;

/**
 * Created by TienBi on 20/07/2016.
 */
public class Background {
    private Image image;

    private double x;
    private double y;

    private double moveScale;

    public Background(double ms) {
        image = ImageLoader.BACKGROUND;
        moveScale = ms;
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GUI.WIDTH;
        this.y = (y * moveScale) % GUI.HEIGHT;
    }

    public void draw(Graphics2D g) {

        g.drawImage(image, (int) x, (int) y, null);

        if (x < 0) {
            g.drawImage(
                    image,
                    (int) x + GUI.WIDTH,
                    (int) y,
                    null
            );
        }
        if (x > 0) {
            g.drawImage(
                    image,
                    (int) x - GUI.WIDTH,
                    (int) y,
                    null
            );
        }
    }
}
