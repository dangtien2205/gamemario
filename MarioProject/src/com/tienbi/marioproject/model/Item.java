package com.tienbi.marioproject.model;

import com.tienbi.marioproject.manager.ImageLoader;

import java.awt.*;

/**
 * Created by TienBi on 24/07/2016.
 */
public class Item extends GameObject {
    private int type;
    private boolean isChangeImage;
    private int index;
    private Animation animation;
    public static int COUNT = 1;
    public static final int TYPE_FLOOR = 1;
    public static final int TYPE_BRICK = 2;
    public static final int TYPE_PIPE1 = 3;
    public static final int TYPE_PIPE2 = 4;
    public static final int TYPE_COIN = 5;
    public static final int TYPE_BOX = 6;
    public static final int TYPE_FLOWER = 7;
    public static final int TYPE_CASTLE = 8;

    public boolean isChangeImage() {
        return isChangeImage;
    }

    public Item(int x, int y, int type) {
        super(x, y);
        this.type = type;
        width = SIZE;
        height = SIZE;

        initType();

        rec.setLocation(x, y);
        rec.setSize(width, height);
    }

    public void initType() {
        switch (type) {
            case TYPE_PIPE1:
                this.y = y - SIZE;
                width = SIZE * 2;
                height = SIZE * 2;
                break;
            case TYPE_PIPE2:
                this.y = y - SIZE * 3;
                width = SIZE * 2;
                height = SIZE * 4;
                break;
            case TYPE_BOX:
                isChangeImage = false;
                animation = new Animation();
                animation.setFrames(ImageLoader.QUESTION_BOX);
                animation.setDelay(300);
                index = COUNT;
                COUNT++;
                break;
            case TYPE_FLOOR:
                height = SIZE * 2;
                break;
            case TYPE_CASTLE:
                width = SIZE * 6;
                height = SIZE * 6;
                break;
            case TYPE_COIN:
                animation = new Animation();
                animation.setFrames(ImageLoader.COIN);
                animation.setDelay(300);
                break;
        }
    }

    public int getIndex() {
        return index;
    }

    public int getType() {
        return type;
    }

    public void setIsChangeImage() {
        isChangeImage = true;
    }

    public boolean getTile(int col, int row) {
        if (x / SIZE == col && y / SIZE == row)
            return true;
        return false;
    }

    public void draw(Graphics2D g2) {
        switch (type) {
            case TYPE_FLOOR:
                g2.drawImage(ImageLoader.IMG_ITEM_FLOOR, (int) xMap + x, y, width, height, null);
                break;
            case TYPE_BRICK:
                g2.drawImage(ImageLoader.IMG_ITEM_BRICK, (int) xMap + x, y, width, height, null);
                break;
            case TYPE_PIPE1:
                g2.drawImage(ImageLoader.IMG_ITEM_PIPE1, (int) xMap + x, y, width, height, null);
                break;
            case TYPE_PIPE2:
                g2.drawImage(ImageLoader.IMG_ITEM_PIPE2, (int) xMap + x, y, width, height, null);
                break;
            case TYPE_COIN:
                animation.update();
                g2.drawImage(animation.getImage(), (int) xMap + x, y, width, height, null);
                break;
            case TYPE_BOX:
                if (isChangeImage)
                    g2.drawImage(ImageLoader.IMG_ITEM_NULLBOX, (int) xMap + x, y, width, height, null);
                else {
                    animation.update();
                    g2.drawImage(animation.getImage(), (int) xMap + x, y, width, height, null);
                }
                break;
            case TYPE_CASTLE:
                g2.drawImage(ImageLoader.IMG_ITEM_CASTLE, (int) xMap + x, y - 5 * SIZE, width, height, null);
                break;
            case TYPE_FLOWER:
                g2.drawImage(ImageLoader.IMG_ITEM_FLOWER, (int) xMap + x, y, width, height, null);
                break;
        }
    }
}
