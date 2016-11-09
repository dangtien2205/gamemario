package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.interfaces.OnClickListener;
import com.tienbi.marioproject.score.ScoreManager;
import com.tienbi.marioproject.sound.PlayerManager;

import java.awt.*;

/**
 * Created by TienBi on 31/07/2016.
 */
public class MyContainer extends BaseContainer implements OnClickListener {
    public static final int SELECTION_PLAYGAME = 0;
    public static final int SELECTION_INSTRUCTION = 1;
    public static final int SELECTION_HIGHSCORE = 2;
    public static final int SELECTION_EXIT = 3;

    private GamePlayPanel gamePlayPanel;
    private InstructionPanel instructionPanel;
    private HighScorePanel highScorePanel;
    private MenuPanel menuPanel;
    private GameOverPanel gameOverPanel;
    private ScorePanel scorePanel;

    private boolean selectionInstruction;

    @Override
    public void initializeContainer() {
        setLayout(new CardLayout());
        setBackground(Color.ORANGE);

        PlayerManager.getInstance().getsBG1().loop(-1);
    }

    @Override
    public void initializeComponents() {
        menuPanel = new MenuPanel();
        menuPanel.setListener(this);
        add(menuPanel);


        instructionPanel = new InstructionPanel();
        instructionPanel.setListener(this);

        highScorePanel = new HighScorePanel();
        highScorePanel.setListener(this);

    }

    @Override
    public void onChange(int selection) {
        remove(menuPanel);
        switch (selection) {
            case SELECTION_PLAYGAME:
                gamePlayPanel = new GamePlayPanel();
                gamePlayPanel.setListener(this);
                ScoreManager.getinstance().getScore();
                add(gamePlayPanel);
                break;
            case SELECTION_INSTRUCTION:
                selectionInstruction = true;
                add(instructionPanel);
                break;
            case SELECTION_HIGHSCORE:
                selectionInstruction = false;
                ScoreManager.getinstance().getScore();
                add(highScorePanel);
                break;
            case SELECTION_EXIT:
                System.exit(0);
                break;
        }
        revalidate();
        repaint();
    }

    @Override
    public void onChangeBack() {
        if (selectionInstruction) remove(instructionPanel);
        else remove(highScorePanel);
        add(menuPanel);
        revalidate();
        repaint();
    }

    @Override
    public void onChangeBackOver() {
        remove(gameOverPanel);
        add(menuPanel);
        revalidate();
        repaint();
    }

    @Override
    public void onChangeScoreBackOver() {
        remove(scorePanel);
        add(menuPanel);
        revalidate();
        repaint();
    }

    @Override
    public void onChangeGameOver(int score, boolean win) {
        remove(gamePlayPanel);
        PlayerManager.getInstance().getsDie().stop();
        gameOverPanel = new GameOverPanel(score, win);
        gameOverPanel.setListener(this);
        add(gameOverPanel);
        revalidate();
        repaint();
    }

    @Override
    public void onChangeScore(int score) {
        remove(gameOverPanel);
        scorePanel = new ScorePanel(score);
        scorePanel.setListener(this);
        add(scorePanel);
        revalidate();
        repaint();
    }
}
