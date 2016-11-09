package com.tienbi.marioproject.manager;

import com.tienbi.marioproject.gui.GamePlayPanel;
import com.tienbi.marioproject.map.Background;
import com.tienbi.marioproject.map.Map;
import com.tienbi.marioproject.score.ScoreManager;
import com.tienbi.marioproject.sound.PlayerManager;
import com.tienbi.marioproject.gui.GUI;
import com.tienbi.marioproject.model.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TienBi on 22/07/2016.
 */
public class GameManager {
    private Map map;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;
    private Mario mario;
    private int healthMario = 5;
    private int score;
    private Background background;
    private int fireBallDamage;
    private int countdownFireByMario;
    private int countdownDeadMario;
    private int countGameOver;
    private int transition;
    private boolean die;
    private static final int MAX_FIRE = 100;
    private static final int MAX_DEAD = 300;
    private static final int MAX_OVER = 300;

    private ArrayList<FireBall> fireBalls;

    public GameManager() {
        mario = new Mario(300, 100, Mario.DOWN);
        die = false;
        countGameOver = MAX_OVER;
        transition = GamePlayPanel.NO_CHANGE;

        map = new Map(Item.SIZE);
        map.loadMap("map1.txt");
        map.setPosition(0, 0);
        populateEnemy();
        background = new Background(0.1);
        items = map.getItems();
        score = 0;
        fireBallDamage = 5;
        fireBalls = new ArrayList<FireBall>();
    }

    private void populateEnemy() {
        enemies = new ArrayList<>();
        Point[] points1 = new Point[]{
                new Point(4000, 100),
                new Point(800, 200),
                new Point(900, 200),
                new Point(1525, 200),
                new Point(1700, 200),
                new Point(1300, 200),
                new Point(3000, 200),
                new Point(3500, 200),
                new Point(3300, 200),
                new Point(4200, 200)
        };
        Point[] points2 = new Point[]{
                new Point(900, 100),
                new Point(1300, 100),
                new Point(4000, 100),
                new Point(1500, 200),
                new Point(1700, 200),
                new Point(2000, 200),
                new Point(2500, 200),
                new Point(3000, 200),
                new Point(3200, 200)
        };
        for (int i = 0; i < points1.length; i++) {
            enemies.add(new Enemy((int) points1[i].getX(), (int) points1[i].getY(), Enemy.TYPE_MUSHROOOM));
        }
        for (int i = 0; i < points2.length; i++) {
            enemies.add(new Enemy((int) points2[i].getX(), (int) points2[i].getY(), Enemy.TYPE_TURTLEGREEN));
        }
        enemies.add(new Enemy(4900,200,Enemy.TYPE_BOSS));
    }

    public void moveEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).getNextPosition();
        }
    }

    public void checkCollisionEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy m = enemies.get(i);
            for (int j = 0; j < items.size(); j++) {
                m.collision(items.get(j));
            }
            m.setLocation();
            m.setMapPosition((int) map.getx(), (int) map.gety());
        }
    }

    public void moveMario() {
        mario.getNextPosition();
    }

    public void checkCollisionMarioWithItem() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (mario.collision(item) == Mario.COLLISION_DISAPPEAR) {
                items.remove(i);
                score += 5;
                if (item.getType() == Item.TYPE_FLOWER) {
                    mario.increaseLevel();
                }
                break;
            }
            if (mario.collision(item) == Mario.COLLISION_CHANGE && !item.isChangeImage()) {
                item.setIsChangeImage();
                if (item.getIndex() == 2)
                    items.add(new Item(item.getX(), item.getY() - GameObject.SIZE, Item.TYPE_FLOWER));
                if (item.getIndex() != 2)
                    items.add(new Item(item.getX(), item.getY() - GameObject.SIZE, Item.TYPE_COIN));
                break;
            }
            if (mario.collision(item) == Mario.COLLISION_WIN) {
                PlayerManager.getInstance().getsBG2().stop();
                //PlayerManager.getInstance().getsCongChua().oneHit();
                transition = GamePlayPanel.WIN_CHANGE;
                break;
            }
        }
        if (die) {
            return;
        }
        mario.setMapPosition((int) map.getx(), (int) map.gety());
        mario.setLocation();
    }

    public void checkCollisionMarioWithEnemy() {
        if (countdownDeadMario == 0) {
            die = false;
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (mario.getRec().intersects(enemy.getRec())) {
                    mario.fallLevel();
                    if (mario.isDead()) {
                        healthMario--;
                        die = true;
                        if (healthMario > 0)
                            PlayerManager.getInstance().getsDie().oneHit();
                        if(healthMario==0){
                            PlayerManager.getInstance().getsDie().stop();
                            PlayerManager.getInstance().getsBG2().stop();
                            PlayerManager.getInstance().getsGameOver().oneHit();
                        }
                    }
                    countdownDeadMario = MAX_DEAD;
                    return;
                }
            }
        } else {
            countdownDeadMario--;
        }
    }

    public void moveMap() {
        map.setPosition(GUI.WIDTH / 2 - mario.getX(), GUI.HEIGHT / 2 - mario.getY());
        background.setPosition(map.getx(), map.gety());
    }

    public void drawEnenmy(Graphics2D graphics2D) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(graphics2D);
        }
    }

    public void drawMario(Graphics2D graphics2D) {
        if (die) {
            mario.drawMarioDie(graphics2D);
        } else {
            mario.checkAnimation();
            mario.draw(graphics2D);
        }
    }

    public void drawMap(Graphics2D graphics2D) {
        map.draw(graphics2D);
    }

    public void drawBackground(Graphics2D graphics2D) {
        background.draw(graphics2D);
    }

    public void drawFireBall(Graphics2D graphics2D) {
        for (int i = 0; i < fireBalls.size(); i++) {
            fireBalls.get(i).draw(graphics2D);
        }
    }

    public void drawScore(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageLoader.MARIO_SCORE, 50, 10, 30, 30, null);
        graphics2D.drawImage(ImageLoader.SCORE[10], 100, 15, 25, 25, null);
        graphics2D.drawImage(ImageLoader.SCORE[healthMario], 140, 15, 25, 25, null);

        graphics2D.drawImage(ImageLoader.SCORE[11], 500, 15, 20, 20, null);
        graphics2D.drawImage(ImageLoader.SCORE[12], 500 + 20 * 1, 15, 20, 20, null);
        graphics2D.drawImage(ImageLoader.SCORE[13], 500 + 20 * 2, 15, 20, 20, null);
        graphics2D.drawImage(ImageLoader.SCORE[14], 500 + 20 * 3, 15, 20, 20, null);
        graphics2D.drawImage(ImageLoader.SCORE[15], 500 + 20 * 4, 15, 20, 20, null);

        String str = "" + score;
        for (int i = 0; i < str.length(); i++) {
            graphics2D.drawImage(ImageLoader.SCORE[str.charAt(i) - '0'], 650 + 20 * i, 15, 20, 20, null);
        }
    }

    public void changeMarioStatus() {
        mario.setJumping();
    }

    public void changeMarioOrient(int orient) {
        mario.setOrient(orient);
    }

    public void fireByMario() {
        if (countdownFireByMario == 0) {
            int t;
            if (mario.getFacingRight()) t = mario.getWidth() / 2;
            else t = -mario.getWidth() / 2;
            FireBall fb = new FireBall(mario.getX() + t, mario.getY() + (mario.getHeight() - GameObject.SIZE) / 2, mario.getFacingRight());
            fireBalls.add(fb);
            PlayerManager.getInstance().getsBump().loop(1);
            countdownFireByMario = MAX_FIRE;
        } else {
            countdownFireByMario -= 5;
        }
    }

    public void checkCollisionFireBall() {
        // update fireballs
        for (int i = 0; i < fireBalls.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (fireBalls.get(i).getHit()) {
                    break;
                }
                Enemy m = enemies.get(j);
                if (fireBalls.get(i).getRec().intersects(m.getRec())) {
                    m.hit(fireBallDamage);
                    if (m.isDead()) {
                        switch (m.getType()){
                            case Enemy.TYPE_MUSHROOOM:
                                score+=5;
                                break;
                            case Enemy.TYPE_TURTLEGREEN:
                                score+=10;
                                break;
                            case Enemy.TYPE_BOSS:
                                score+=50;
                                break;
                        }
                        enemies.remove(j);
                    }
                    fireBalls.get(i).setHit();
                    break;
                }
            }
            if (!fireBalls.get(i).getHit()) {
                for (int j = 0; j < items.size(); j++) {
                    if (fireBalls.get(i).collision(items.get(j))) {
                        fireBalls.get(i).setHit();
                        break;
                    }
                }
            }
            double xTotal = fireBalls.get(i).getX() + map.getx();
            if (fireBalls.get(i).getRemove() || xTotal <= 0 || xTotal >= GUI.WIDTH - GameObject.SIZE) {
                fireBalls.remove(i);
                i--;
            } else {
                fireBalls.get(i).setMapPosition((int) map.getx(), (int) map.gety());
                fireBalls.get(i).setLocation();
            }
        }
    }

    public void checkHealthMario() {
        if (healthMario == 0) {
            if(countGameOver==0) transition = GamePlayPanel.GAMEOVER_CHANGE;
            else countGameOver --;
        }
    }

    public int getTransition() {
        return transition;
    }

    public int getScore() {
        return score;
    }
}
