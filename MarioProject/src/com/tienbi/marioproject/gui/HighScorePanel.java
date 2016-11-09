package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.manager.ImageLoader;
import com.tienbi.marioproject.score.Score;
import com.tienbi.marioproject.score.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by TienBi on 31/07/2016.
 */
public class HighScorePanel extends SelectionPanel {
    @Override
    public void initializeContainer() {
        setLayout(null);
    }

    @Override
    public void initializeComponents() {
        JButton btnBack =new JButton();
        btnBack.setBounds((GUI.WIDTH-180)/2,470,180,30);
        btnBack.setIcon(ImageLoader.IMG_MENU_BACK);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChangeBack();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        requestFocus();
        Font font = new Font("times new roman",Font.BOLD,32);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setFont(font);
        graphics2D.drawImage(ImageLoader.IMG_SCORE,0,0,GUI.WIDTH,GUI.HEIGHT-30,null);
        ArrayList<Score> list = ScoreManager.getinstance().getArrScore();
        for (int i = 0; i < list.size() ; i++) {
            graphics2D.drawString(list.get(i).getName(),300,250+i*30);
            graphics2D.drawString(list.get(i).getNum()+"",450,250+i*30);
        }
    }
}
