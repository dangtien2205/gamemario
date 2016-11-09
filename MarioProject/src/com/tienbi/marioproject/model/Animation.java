package com.tienbi.marioproject.model;

import java.awt.*;

/**
 * Created by TienBi on 21/07/2016.
 */
public class Animation {
    private Image[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    public Animation() {
        playedOnce = false;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }

    public void update() {

        if (delay == -1) return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public Image getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }
}
