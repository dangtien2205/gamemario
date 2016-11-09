package com.tienbi.marioproject.gui;


import com.tienbi.marioproject.score.ScoreManager;
import com.tienbi.marioproject.sound.PlayerManager;
import com.tienbi.marioproject.manager.GameManager;
import com.tienbi.marioproject.model.Mario;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * Created by TienBi on 17/07/2016.
 */
public class GamePlayPanel extends SelectionPanel implements Runnable {
    public static int NO_CHANGE = 1;
    public static int GAMEOVER_CHANGE = 2;
    public static int WIN_CHANGE = 3;

    private GameManager gameManager;
    private BitSet bitSet;
    private KeyAdapter keyAdapter;
    private boolean isRunning;
    private Thread thread;
    private boolean pause;

    public GamePlayPanel() {
        super();
    }

    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.CYAN);

        PlayerManager.getInstance().getsBG1().stop();
        PlayerManager.getInstance().getsBG2().loop(-1);
        PlayerManager.getInstance().getsPlay().play();
    }

    @Override
    public void initializeComponents() {
        initializeListener();

        isRunning = false;
        gameManager = new GameManager();
        startGame();
    }

    public void initializeListener() {
        bitSet = new BitSet(256);
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.clear(e.getKeyCode());
                gameManager.changeMarioOrient(Mario.DOWN);
            }
        };
        addKeyListener(keyAdapter);
        setFocusable(true);
    }

    public void handleChangeMarioOrient() {
        if (bitSet.get(KeyEvent.VK_LEFT)) {
            gameManager.changeMarioOrient(Mario.LEFT);
        }
        if (bitSet.get(KeyEvent.VK_UP)) {
            gameManager.changeMarioStatus();
            PlayerManager.getInstance().getsJump().oneHit();
        }
        if (bitSet.get(KeyEvent.VK_RIGHT)) {
            gameManager.changeMarioOrient(Mario.RIGHT);
        }
        if (bitSet.get(KeyEvent.VK_DOWN)) {
            gameManager.changeMarioOrient(Mario.DOWN);
        }
        if (bitSet.get(KeyEvent.VK_SPACE)) {
            gameManager.fireByMario();
        }
        if (bitSet.get(KeyEvent.VK_P)) {
            if (pause) pause = false;
            else pause = true;
        }
        gameManager.moveMario();
        gameManager.moveMap();
        gameManager.moveEnemy();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        gameManager.drawBackground(graphics2D);
        gameManager.drawMap(graphics2D);
        gameManager.drawMario(graphics2D);
        gameManager.drawEnenmy(graphics2D);
        gameManager.drawFireBall(graphics2D);
        gameManager.drawScore(graphics2D);
    }

    public void checkGameManager() {
        if (pause) return;
        gameManager.checkCollisionMarioWithItem();
        gameManager.checkCollisionMarioWithEnemy();
        gameManager.checkHealthMario();
        gameManager.checkCollisionFireBall();
        gameManager.checkCollisionEnemy();
    }

    public void checkTransition() {
        int n = gameManager.getTransition();
        if (n == NO_CHANGE) return;
        ScoreManager.getinstance().getScore();
        if (n == WIN_CHANGE)
            listener.onChangeGameOver(gameManager.getScore(), true);
        if (n == GAMEOVER_CHANGE)
            listener.onChangeGameOver(gameManager.getScore(), false);
        gameManager = new GameManager();
    }

    public void startGame() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            requestFocus();

            checkTransition();
            handleChangeMarioOrient();
            checkGameManager();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
