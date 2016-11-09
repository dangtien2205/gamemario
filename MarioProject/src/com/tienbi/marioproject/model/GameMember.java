package com.tienbi.marioproject.model;

import java.awt.*;

/**
 * Created by TienBi on 30/07/2016.
 */
public class GameMember extends GameObject {
    protected boolean jumping;
    protected boolean falling;
    // movement attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    // animation
    protected Animation animation;
    protected int currentAction;
    protected boolean facingRight;

    public GameMember(int x, int y) {
        super(x, y);
        width = SIZE ;
        height = SIZE ;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(facingRight) {
            g2.drawImage(
                    animation.getImage(),
                    (int) (x + xMap),
                    (int) (y + yMap),
                    width,
                    height,
                    null
            );
        }
        else {
            g2.drawImage(
                    animation.getImage(),
                    (int)(x + xMap +width),
                    (int)(y + yMap ),
                    -width,
                    height,
                    null
            );
        }
    }
}
