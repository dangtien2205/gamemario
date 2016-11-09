package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.manager.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by TienBi on 05/08/2016.
 */
public class InstructionPanel extends SelectionPanel {
    private KeyAdapter keyAdapter;
    @Override
    public void initializeContainer() {
        setLayout(null);
    }

    @Override
    public void initializeComponents() {
        initializeListener();

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
    public void initializeListener() {
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    listener.onChangeBack();
                }
            }
        };
        addKeyListener(keyAdapter);
        setFocusable(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        requestFocus();
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(ImageLoader.IMG_INSTRUCTION,0,0,GUI.WIDTH,GUI.HEIGHT-30,null);
    }
}
