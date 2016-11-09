package com.tienbi.marioproject.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by TienBi on 21/07/2016.
 */
public class FireBall extends GameMember {
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public boolean getHit() {
        return hit;
    }

    public FireBall(int x,int y,boolean right) {
        super(x,y);
        facingRight = right;

        moveSpeed = 3.8;
        if(right) dx = moveSpeed;
        else dx = -moveSpeed;

        loadAnimation();
    }
    public void loadAnimation(){
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResource(
                            "/res/images/fireball.gif"
                    )
            );

            sprites = new BufferedImage[4];
            for(int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(
                        i *30,
                        0,
                        30,
                        30
                );
            }

            hitSprites = new BufferedImage[3];
            for(int i = 0; i < hitSprites.length; i++) {
                hitSprites[i] = spritesheet.getSubimage(
                        i * 30,
                        30,
                        30,
                        30
                );
            }

            animation = new Animation();
            animation.setFrames(sprites);

            animation.setDelay(70);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void setHit() {
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }

    public boolean getRemove() { return remove; }

    public boolean collision(Item o) {
        int xx=x;
        int yy=y;
        Rectangle rec = new Rectangle(xx, yy, width, height);
        if(!facingRight ) {
            xx -= 1;
            rec.setLocation(xx, yy);
            if (rec.intersects(o.getRec())) {
                dx = 0;
                return true;
            }
        }
        else {
            xx+=1;
            rec.setLocation(xx,yy);
            if(rec.intersects(o.getRec())) {
                dx = 0;
                return true;
            }
        }
        return false;
    }
    public void setLocation(){
        x=(int)(x+dx);
        y=(int)(y+dy);
        animation.update();
        if(hit && animation.hasPlayedOnce()) {
            remove = true;
        }
    }
}
