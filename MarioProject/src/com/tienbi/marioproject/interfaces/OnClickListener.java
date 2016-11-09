package com.tienbi.marioproject.interfaces;

/**
 * Created by TienBi on 31/07/2016.
 */
public interface OnClickListener {
    public void onChange(int selection);
    public void onChangeBack();
    public void onChangeBackOver();
    public void onChangeScoreBackOver();
    public void onChangeGameOver(int score ,boolean win);
    public void onChangeScore(int score);
}
