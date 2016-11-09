package com.tienbi.marioproject.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.tienbi.marioproject.manager.ImageLoader;
import com.tienbi.marioproject.score.ScoreManager;
import com.tienbi.marioproject.sound.PlayerManager;

import javax.swing.*;
import java.awt.*;


/**
 * Created by TienBi on 05/08/2016.
 */
public class GameOverPanel extends SelectionPanel{
    private int score;
    private boolean win;
    public GameOverPanel(){};
    public GameOverPanel(int score ,boolean win){
        super();
        this.score =score;
        this.win =win;
        if(win){
            PlayerManager.getInstance().getsWin().loop(-1);
        }else {
            PlayerManager.getInstance().getsLose().loop(-1);
        }
        initOther();
    }
    @Override
    public void initializeContainer() {
        setLayout(null);
    }

    @Override
    public void initializeComponents() {
        JButton btnPlayAgain =new JButton();
        btnPlayAgain.setBounds((GUI.WIDTH-180)/2,250,180,30);
        btnPlayAgain.setIcon(ImageLoader.IMG_MENU_BACK);
        add(btnPlayAgain);
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChangeBackOver();
            }
        });

        JButton btnExit =new JButton();
        btnExit.setBounds((GUI.WIDTH-180)/2,300,180,30);
        btnExit.setIcon(ImageLoader.IMG_MENU_EXIT);
        add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void initOther(){
        if(ScoreManager.getinstance().checkScore(score)){
            JButton btnImport =new JButton();
            btnImport.setBounds((GUI.WIDTH-180)/2,200,180,30);
            btnImport.setIcon(ImageLoader.IMG_IMPORT_NAME);
            add(btnImport);
            btnImport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ScoreManager.getinstance().getArrScore();
                    listener.onChangeScore(score);
                }
            });
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        requestFocus();
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(ImageLoader.IMG_GAMEOVER,0,0,GUI.WIDTH,GUI.HEIGHT-30,null);
        String str;
        Font font = new Font("times new roman",Font.ROMAN_BASELINE,32);
        if(win) str="Chúc mừng bạn chiến thắng";
        else str =  "Rất tiếc !Mời bạn chơi lại";
        graphics2D.setFont(font);
        graphics2D.drawString(str,250,170);
    }
}
