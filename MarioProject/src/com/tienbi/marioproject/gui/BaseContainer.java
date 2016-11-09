package com.tienbi.marioproject.gui;

import javax.swing.*;

/**
 * Created by TienBi on 17/07/2016.
 */
public abstract class BaseContainer extends JPanel {
    public BaseContainer() {
        initializeContainer();
        initializeComponents();
    }

    public abstract void initializeContainer();

    public abstract void initializeComponents();

}
