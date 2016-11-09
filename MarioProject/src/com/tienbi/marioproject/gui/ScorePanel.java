package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.manager.ImageLoader;
import com.tienbi.marioproject.score.Score;
import com.tienbi.marioproject.score.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by TienBi on 08/08/2016.
 */
public class ScorePanel extends SelectionPanel {
    private KeyAdapter keyAdapter;
    private int score;
    public ScorePanel(int score){
        super();
        this.score=score;
    }
    @Override
    public void initializeContainer() {
        setLayout(null);
    }

    @Override
    public void initializeComponents() {

        JTextField tfImport = new JTextField();
        tfImport.setBounds(40,250,150,30);
        add(tfImport);

        JButton btnImport =new JButton();
        btnImport.setBounds(40,300,150,30);
        btnImport.setIcon(ImageLoader.IMG_IMPORT_NAME);
        add(btnImport);
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfImport.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"chưa nhập tên");
                    return;
                }
                btnImport.setEnabled(false);
                ScoreManager.getinstance().insertHighScore(tfImport.getText(),score);
                ScoreManager.getinstance().writeScore();
                repaint();
            }
        });

        JButton btnBack =new JButton();
        btnBack.setBounds((GUI.WIDTH-180)/2,470,180,30);
        btnBack.setIcon(ImageLoader.IMG_MENU_BACK);
        add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChangeScoreBackOver();
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
