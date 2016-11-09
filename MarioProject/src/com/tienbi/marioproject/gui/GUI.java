package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.score.ScoreManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TienBi on 17/07/2016.
 */
public class GUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT =40*13+30;

    public GUI() {
        initialize();
        initializeListener();
        initializeComponents();
    }

    private void initialize() {
        setLayout(new CardLayout());
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Mario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initializeListener() {
        /*windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int r = JOptionPane.showConfirmDialog(null,"Bạn có muốn thoát không ?","",JOptionPane.YES_NO_OPTION);
                if(r==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        };
        addWindowListener(windowAdapter);*/
    }
    private void initializeComponents() {
        add(new MyContainer());
    }
}