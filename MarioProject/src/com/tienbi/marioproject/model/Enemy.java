package com.tienbi.marioproject.model;

import com.tienbi.marioproject.manager.ImageLoader;

import java.awt.*;

/**
 * Created by TienBi on 31/07/2016.
 */
public class Enemy extends GameMember {
    private int health;
    private boolean dead;
    private int type;
    private Image[] images;
    public static final int TYPE_MUSHROOOM = 1;
    public static final int TYPE_TURTLEGREEN = 2;
    public static final int TYPE_BOSS = 3;

    public Enemy(int x, int y, int type) {
        super(x, y);
        width = SIZE;
        height = SIZE;
        facingRight = true;
        falling = true;
        this.type = type;
        initializeType();
    }

    public void initializeType() {
        switch (type) {
            case TYPE_MUSHROOOM:
                health = 5;
                moveSpeed = 0.5;
                maxSpeed = 1;
                fallSpeed = 0.2;
                maxFallSpeed = 2.0;

                images = ImageLoader.MUSHROOM;
                break;
            case TYPE_TURTLEGREEN:
                health = 10;
                moveSpeed = 0.5;
                maxSpeed = 1;
                fallSpeed = 0.2;
                maxFallSpeed = 2.0;

                images = ImageLoader.TURTLEGREEN;
                break;

            case TYPE_BOSS:
                health = 25;
                moveSpeed = 0.5;
                maxSpeed = 1;
                fallSpeed = 0.2;
                maxFallSpeed = 1.5;
                facingRight = false;

                images = ImageLoader.BOSS;
                break;
        }
        animation = new Animation();
        animation.setFrames(images);
        animation.setDelay(300);
    }

    public boolean isDead() {
        return dead;
    }

    public void hit(int damage) {
        if (dead) return;
        health -= damage;
        if (health < 0) health = 0;
        if (health == 0) dead = true;
    }

    public void getNextPosition() {
        // movement
        if (!facingRight) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }

        // falling
        if (falling) {
            dy += fallSpeed;
            if (dy >= maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }

    }

    public int getType() {
        return type;
    }

    public void collision(Item item) {
        int xx = x;
        int yy = y;

        Rectangle rec = new Rectangle(xx, yy, width, height);
        if (!facingRight) {
            xx -= 1;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dx = 0;
                facingRight = true;
            }
        }
        if (facingRight) {
            xx += 1;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dx = 0;
                facingRight = false;
            }
        }
        if (falling) {
            yy += 2;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dy = 0;
                if (type == TYPE_TURTLEGREEN || type == TYPE_BOSS) dy = -9;
            }
        }
    }

    public void setLocation() {
        if (type == TYPE_BOSS)
            dx = 0;
        x = (int) (x + dx);
        y = (int) (y + dy);
        animation.update();
    }
}
