package com.tienbi.marioproject.model;

import java.awt.*;

/**
 * Created by TienBi on 22/07/2016.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    public static final int SIZE = 40;
    protected Rectangle rec;

    protected double xMap;
    protected double yMap;

    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        rec = new Rectangle(x, y, SIZE, SIZE);
    }

    public void setMapPosition(float xM, float yM) {
        xMap = xM;
        yMap = yM;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRec() {
        rec.setLocation(x, y);
        return rec;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(Graphics2D g2);
}
