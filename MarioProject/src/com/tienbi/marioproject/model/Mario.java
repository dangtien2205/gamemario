package com.tienbi.marioproject.model;


import com.tienbi.marioproject.sound.PlayerManager;
import com.tienbi.marioproject.manager.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TienBi on 22/07/2016.
 */
public class Mario extends GameMember {
    public static final int COLLISION_NOTHING = 0;
    public static final int COLLISION_CHANGE = 1;
    public static final int COLLISION_DISAPPEAR = 2;
    public static final int COLLISION_WIN = 3;

    public static final int MAX_LEVELMARIO = 2;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int OTHER = 3;

    private static final int IDLE = 0;
    private static final int FALLING = 1;
    private static final int WALKING = 2;
    private static final int JUMPING = 3;

    private ArrayList<Image[]> images;
    private int levelMario;

    private int orient;
    private int yJump;
    private boolean dead;

    public Mario(int x, int y, int orient) {
        super(x, y);
        moveSpeed = 0.4;
        maxSpeed = 1.5;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4;
        jumpStart = -6.6;
        stopJumpSpeed = 0.3;
        levelMario = 1;
        this.orient = orient;
        width = height = SIZE;
        dead = false;

        facingRight = true;
        falling = true;
        loadAnimation();
    }

    public boolean isDead() {
        return dead;
    }

    public void increaseLevel() {
        if (levelMario == MAX_LEVELMARIO) return;
        PlayerManager.getInstance().getsPowerUp().oneHit();
        levelMario++;
        if (levelMario == MAX_LEVELMARIO) {
            y -= SIZE;
            height = SIZE * 2;

            rec.setSize(width, height);
            rec.setLocation(x, y);
        }
    }

    public void fallLevel() {
        if (levelMario != 1) {
            levelMario = 1;
            height = SIZE;
            rec.setSize(width, height);
            PlayerManager.getInstance().getsLevelDown().oneHit();
        } else {
            dead = true;
        }
    }

    public void setOrient(int orient) {
        this.orient = orient;
        if (orient == RIGHT)
            facingRight = true;
        if (orient == LEFT)
            facingRight = false;
    }

    public boolean getFacingRight() {
        return facingRight;
    }

    public void loadAnimation() {
        images = ImageLoader.MARIO_LEVEL1;
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(images.get(IDLE));
        animation.setDelay(300);
    }

    public void drawMarioDie(Graphics2D graphics2D) {
        graphics2D.drawImage(
                ImageLoader.MARIO_DIE,
                (int) (x + xMap),
                (int) (y + yMap),
                width,
                height,
                null
        );
    }

    public void checkAnimation() {
        if (levelMario == MAX_LEVELMARIO) {
            images = ImageLoader.MARIO_LEVEL2;
        } else {
            images = ImageLoader.MARIO_LEVEL1;
        }
        if (dy > 0) {
            if (currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(images.get(FALLING));
                animation.setDelay(100);
            }
        } else if (dy < 0) {
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(images.get(JUMPING));
                animation.setDelay(-1);
            }
        } else if (orient == LEFT || orient == RIGHT) {
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(images.get(WALKING));
                animation.setDelay(40);
            }
        } else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(images.get(IDLE));
                animation.setDelay(400);
            }
        }
        animation.update();
    }

    public void getNextPosition() {
        // movement
        if (orient == LEFT) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed + 0.3;
            }
        } else if (orient == RIGHT) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed + 0.6;
            }
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }


        // jumping
        if (jumping && !falling) {
            //sfx.get("jump").play();
            dy = jumpStart;
        }

        // falling
        if (falling) {
            if (dy > 0) {
                dy += fallSpeed;
            } else dy += fallSpeed;
            if (dy > 0) {
                jumping = false;
            }
            if (dy < 0 && !jumping) {
                dy += stopJumpSpeed;
            }

            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
    }

    public int collision(Item item) {
        int xx = x;
        int yy = y;
        Rectangle rec = new Rectangle(xx - 1, yy - 10, width + 2, height + 20);
        if (rec.intersects(item.getRec())) {
            if (item.getType() == Item.TYPE_CASTLE) {
                return COLLISION_WIN;
            }
            if (item.getType() == Item.TYPE_FLOWER) {
                return COLLISION_DISAPPEAR;
            }
            if (item.getType() == Item.TYPE_COIN) {
                PlayerManager.getInstance().getsCoin().oneHit();
                return COLLISION_DISAPPEAR;
            }
        }
        rec.setSize(width, height);
        if (orient == LEFT) {
            xx -= 1;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dx = 0;
                orient = DOWN;
            }
        }
        if (orient == RIGHT) {
            xx += 1;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dx = 0;
                orient = DOWN;
            }
        }
        if (falling) {
            yy += 4;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                dy = 0;
            }
        }
        if (jumping || orient == DOWN) {
            yy -= 4;
            rec.setLocation(xx, yy);
            if (rec.intersects(item.getRec())) {
                jumping = false;
                falling = true;
                dy = 4;
                if (item.getType() == Item.TYPE_BOX)
                    return COLLISION_CHANGE;
            }
        }
        return COLLISION_NOTHING;
    }

    public void setJumping() {
        jumping = true;
        falling = false;
        yJump = y;
    }

    public void setLocation() {
        x = (int) (x + dx);
        y = (int) (y + dy);
        if (yJump < y + SIZE * 5) {
            jumping = false;
            falling = true;
        }
    }
}
