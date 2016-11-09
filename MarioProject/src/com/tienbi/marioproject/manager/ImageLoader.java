package com.tienbi.marioproject.manager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by TienBi on 24/07/2016.
 */
public class ImageLoader {
    public static final Image IMG_ITEM_FLOOR = new ImageIcon(getURL("floor1.png")).getImage();
    public static final Image IMG_ITEM_BRICK = new ImageIcon(getURL("brick.png")).getImage();
    public static final Image IMG_ITEM_PIPE1 = new ImageIcon(getURL("pipe.png")).getImage();
    public static final Image IMG_ITEM_PIPE2 = new ImageIcon(getURL("pipe2.png")).getImage();
    public static final Image IMG_ITEM_NULLBOX = new ImageIcon(getURL("null_box.png")).getImage();
    public static final Image IMG_ITEM_FLOWER = new ImageIcon(getURL("flower.png")).getImage();
    public static final Image IMG_ITEM_CASTLE = new ImageIcon(getURL("mario_castle.png")).getImage();
    public static final Image BACKGROUND = new ImageIcon(getURL("Fence2.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_11 = new ImageIcon(getURL("smallmario_move_right_1.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_12 = new ImageIcon(getURL("mario_move_right_1.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_21 = new ImageIcon(getURL("smallmario_move_right_2.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_22 = new ImageIcon(getURL("mario_move_right_2.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_31 = new ImageIcon(getURL("smallmario_move_right_3.png")).getImage();
    public static final Image MARIO_SMALL_MOVE_32 = new ImageIcon(getURL("mario_move_right_3.png")).getImage();
    public static final Image MARIO_SMALL_JUMP1 = new ImageIcon(getURL("smallmario_jump_right.png")).getImage();
    public static final Image MARIO_SMALL_JUMP2 = new ImageIcon(getURL("mario_jump_right.png")).getImage();
    public static final Image MARIO_STAND1 = new ImageIcon(getURL("smallmario_stand_right.png")).getImage();
    public static final Image MARIO_STAND2 = new ImageIcon(getURL("mario_stand_right.png")).getImage();
    public static final Image MARIO_SCORE = new ImageIcon(getURL("mario_sit_left.png")).getImage();
    public static final Image MARIO_DIE = new ImageIcon(getURL("SmallDeadMario.png")).getImage();
    public static final Image IMG_MENU = new ImageIcon(getURL("menu_background.png")).getImage();
    public static final Image IMG_INSTRUCTION = new ImageIcon(getURL("instruction.png")).getImage();
    public static final Image IMG_SCORE = new ImageIcon(getURL("menu_highscores.png")).getImage();
    public static final Image IMG_GAMEOVER = new ImageIcon(getURL("gameover.png")).getImage();
    public static final ImageIcon IMG_MENU_PLAY = new ImageIcon(getURL("menu_button_play.png"));
    public static final ImageIcon IMG_MENU_PLAY1 = new ImageIcon(getURL("btn_playgame.png"));
    public static final ImageIcon IMG_MENU_INSTRUCTION = new ImageIcon(getURL("menu_button_Instruction.png"));
    public static final ImageIcon IMG_MENU_INSTRUCTION1 = new ImageIcon(getURL("btn_instruction.png"));
    public static final ImageIcon IMG_MENU_HIGHSCORES = new ImageIcon(getURL("menu_button_highscores.png"));
    public static final ImageIcon IMG_MENU_HIGHSCORES1 = new ImageIcon(getURL("btn_highscores.png"));
    public static final ImageIcon IMG_MENU_EXIT = new ImageIcon(getURL("menu_button_exit.png"));
    public static final ImageIcon IMG_MENU_EXIT1 = new ImageIcon(getURL("btn_exit.png"));
    public static final ImageIcon IMG_MENU_BACK = new ImageIcon(getURL("instruction_back.png"));
    public static final ImageIcon IMG_IMPORT_NAME = new ImageIcon(getURL("button.png"));
    public static final Image[] MUSHROOM = getMushroom();
    public static final Image[] SCORE = getScore();
    public static final Image[] TURTLEGREEN = getTurtleGreen();
    public static final Image[] BOSS = getBoss() ;
    public static final Image[] COIN = getCoin();
    public static final Image[] QUESTION_BOX = getQuestionBox();
    public static final ArrayList<Image[]> MARIO_LEVEL1 = getMario1();
    public static final ArrayList<Image[]> MARIO_LEVEL2 = getMario2();

    private ImageLoader() {
    }

    private static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/images/" + fileName);
    }

    private static Image[] getMushroom() {
        Image[] images = new Image[2];
        images[0] = new ImageIcon(getURL("ghost_left.png")).getImage();
        images[1] = new ImageIcon(getURL("ghost_right.png")).getImage();
        return images;
    }

    private static Image[] getTurtleGreen() {
        Image[] images = new Image[2];
        BufferedImage spritesheet;
        try {
            spritesheet = ImageIO.read(
                    ImageLoader.class.getResource(
                            "/res/images/TurtleGreen.png"
                    )
            );
            images[0] = spritesheet.getSubimage(32 * 2, 0, 32, 48);
            images[1] = spritesheet.getSubimage(32 * 3, 0, 32, 48);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private static Image[] getBoss() {
        Image[] images = new Image[3];
        BufferedImage spritesheet;
        try {
            spritesheet = ImageIO.read(
                    ImageLoader.class.getResource(
                            "/res/images/Boss.png"
                    )
            );
            images[0] = spritesheet.getSubimage(64 * 0, 64, 32, 64);
            images[1] = spritesheet.getSubimage(64 * 1, 64, 32, 64);
            images[1] = spritesheet.getSubimage(64 * 2, 64, 64, 64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private static Image[] getCoin() {
        Image[] images = new Image[3];
        BufferedImage spritesheet;
        try {
            spritesheet = ImageIO.read(
                    ImageLoader.class.getResource(
                            "/res/images/Coin.png"
                    )
            );
            images[0] = spritesheet.getSubimage(32 * 0, 0, 32, 32);
            images[1] = spritesheet.getSubimage(32 * 1, 0, 32, 32);
            images[2] = spritesheet.getSubimage(32 * 2, 0, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private static Image[] getQuestionBox() {
        Image[] images = new Image[3];
        BufferedImage spritesheet;
        try {
            spritesheet = ImageIO.read(
                    ImageLoader.class.getResource(
                            "/res/images/QuestionMarkGrey.png"
                    )
            );
            images[0] = spritesheet.getSubimage(32 * 0, 0, 32, 32);
            images[1] = spritesheet.getSubimage(32 * 1, 0, 32, 32);
            images[2] = spritesheet.getSubimage(32 * 2, 0, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private static Image[] getScore() {
        Image[] images = new Image[16];
        BufferedImage spritesheet;
        try {
            spritesheet = ImageIO.read(
                    ImageLoader.class.getResource(
                            "/res/images/Font.png"
                    )
            );
            for (int i = 0; i <= 9; i++) {
                images[i] = spritesheet.getSubimage((i + 1) * 16, 0, 16, 16);
            }
            images[10] = spritesheet.getSubimage(10 * 16, 32, 16, 16);
            images[11] = spritesheet.getSubimage(13 * 16, 16, 16, 16);
            images[12] = spritesheet.getSubimage(13 * 16, 0, 16, 16);
            images[13] = spritesheet.getSubimage(9 * 16, 16, 16, 16);
            images[14] = spritesheet.getSubimage(12 * 16, 16, 16, 16);
            images[15] = spritesheet.getSubimage(15 * 16, 0, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }

    private static ArrayList<Image[]> getMario1() {
        ArrayList<Image[]> images = new ArrayList<>();
        Image[] bi = new Image[1];
        bi[0] = ImageLoader.MARIO_STAND1;
        images.add(bi);
        images.add(bi);

        bi = new Image[3];
        bi[0] = ImageLoader.MARIO_SMALL_MOVE_11;
        bi[1] = ImageLoader.MARIO_SMALL_MOVE_21;
        bi[2] = ImageLoader.MARIO_SMALL_MOVE_31;
        images.add(bi);

        bi = new Image[1];
        bi[0] = ImageLoader.MARIO_SMALL_JUMP1;
        images.add(bi);
        return images;
    }

    private static ArrayList<Image[]> getMario2() {
        ArrayList<Image[]> images = new ArrayList<>();
        Image[] bi = new Image[1];
        bi[0] = ImageLoader.MARIO_STAND2;
        images.add(bi);
        images.add(bi);

        bi = new Image[3];
        bi[0] = ImageLoader.MARIO_SMALL_MOVE_12;
        bi[1] = ImageLoader.MARIO_SMALL_MOVE_22;
        bi[2] = ImageLoader.MARIO_SMALL_MOVE_32;
        images.add(bi);

        bi = new Image[1];
        bi[0] = ImageLoader.MARIO_SMALL_JUMP2;
        images.add(bi);
        return images;
    }
}
