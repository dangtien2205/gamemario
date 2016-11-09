package com.tienbi.marioproject.sound;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by TienBi on 05/08/2016.
 */
public class PlayerWav implements LineListener {
    private Clip clip;
    private boolean isRunning = false;
    private AudioInputStream input;
    private boolean t;

    public PlayerWav(String filename) {
        URL url = getClass().getResource("/res/sounds/" + filename + ".wav");
        try {
            clip = AudioSystem.getClip();
            input = AudioSystem.getAudioInputStream(url);
            clip.open(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t=false;
    }

    public void play() {
        if (clip.isRunning()) {
            return;
        }
        clip.start();

        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        clip.stop();
        clip.close();
    }
    public void loop(int number){
        clip.loop(number);
    }
    public void oneHit(){
        if(!t) {
            clip.start();
            t=true;
            return;
        }
        clip.loop(1);
    }
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP
                || event.getType() == LineEvent.Type.CLOSE) {
            isRunning = false;
        }
    }
}
