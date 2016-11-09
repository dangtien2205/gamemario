package com.tienbi.marioproject.gui;

import com.tienbi.marioproject.interfaces.OnClickListener;

/**
 * Created by TienBi on 05/08/2016.
 */
public class SelectionPanel extends BaseContainer{
    protected OnClickListener listener;
    @Override
    public void initializeContainer() {
    }

    @Override
    public void initializeComponents() {
    }
    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
