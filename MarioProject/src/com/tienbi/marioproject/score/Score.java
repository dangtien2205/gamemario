package com.tienbi.marioproject.score;

/**
 * Created by TienBi on 06/08/2016.
 */
public class Score {
    private String name;
    private int num;

    public Score(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
