package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.sound.PlayerManager;
import com.tienbi.marioproject.manager.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by TienBi on 31/07/2016.
 */
public class MenuPanel extends SelectionPanel implements ActionListener {
    private KeyAdapter keyAdapter;
    private JButton btnPlayGame;
    private JButton btnInstruction;
    private JButton btnHighScores;
    private JButton btnExit;
    private ArrayList<JButton> listButton;
    private int index;

    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.RED);
    }

    @Override
    public void initializeComponents() {
        index = 0;
        initializeListener();

        btnPlayGame = new JButton();
        btnPlayGame.setBounds((GUI.WIDTH - 180) / 2, 145, 180, 30);
        btnPlayGame.setIcon(ImageLoader.IMG_MENU_PLAY);
        btnPlayGame.setSelectedIcon(ImageLoader.IMG_MENU_PLAY1);
        btnPlayGame.setSelected(true);

        btnInstruction = new JButton();
        btnInstruction.setBounds((GUI.WIDTH - 180) / 2, 185, 180, 30);
        btnInstruction.setIcon(ImageLoader.IMG_MENU_INSTRUCTION);
        btnInstruction.setSelectedIcon(ImageLoader.IMG_MENU_INSTRUCTION1);

        btnHighScores = new JButton();
        btnHighScores.setBounds((GUI.WIDTH - 180) / 2, 225, 180, 30);
        btnHighScores.setIcon(ImageLoader.IMG_MENU_HIGHSCORES);
        btnHighScores.setSelectedIcon(ImageLoader.IMG_MENU_HIGHSCORES1);

        btnExit = new JButton();
        btnExit.setBounds((GUI.WIDTH - 180) / 2, 265, 180, 30);
        btnExit.setIcon(ImageLoader.IMG_MENU_EXIT);
        btnExit.setSelectedIcon(ImageLoader.IMG_MENU_EXIT1);

        add(btnPlayGame);
        add(btnInstruction);
        add(btnHighScores);
        add(btnExit);

        listButton = new ArrayList<>();
        listButton.add(btnPlayGame);
        listButton.add(btnInstruction);
        listButton.add(btnHighScores);
        listButton.add(btnExit);

        btnPlayGame.setActionCommand("play");
        btnPlayGame.addActionListener(this);

        btnInstruction.setActionCommand("instruction");
        btnInstruction.addActionListener(this);

        btnHighScores.setActionCommand("highscores");
        btnHighScores.addActionListener(this);

        btnExit.setActionCommand("exit");
        btnExit.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        requestFocus();
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(ImageLoader.IMG_MENU, 0, 0, GUI.WIDTH, GUI.HEIGHT - 30, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "play":
                listener.onChange(MyContainer.SELECTION_PLAYGAME);
                break;
            case "instruction":
                listener.onChange(MyContainer.SELECTION_INSTRUCTION);
                break;
            case "highscores":
                listener.onChange(MyContainer.SELECTION_HIGHSCORE);
                break;
            case "exit":
                listener.onChange(MyContainer.SELECTION_EXIT);
                break;
        }
    }

    public void initializeListener() {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    PlayerManager.getInstance().getsClick().oneHit();
                    index++;
                    if (index == 4) {
                        index = 0;
                        listButton.get(listButton.size() - 1).setSelected(false);
                    } else {
                        listButton.get(index - 1).setSelected(false);
                    }
                    listButton.get(index).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    PlayerManager.getInstance().getsClick().oneHit();
                    index--;
                    if (index == -1) {
                        index = 3;
                        listButton.get(0).setSelected(false);
                    } else {
                        listButton.get(index + 1).setSelected(false);
                    }
                    listButton.get(index).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    listener.onChange(index);
                }
            }
        };
        addKeyListener(keyAdapter);
        setFocusable(true);
    }
}
