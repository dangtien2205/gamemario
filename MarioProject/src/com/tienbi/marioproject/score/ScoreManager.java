package com.tienbi.marioproject.score;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by TienBi on 06/08/2016.
 */
public class ScoreManager {
    private ArrayList<Score> arrScore;
    private RandomAccessFile raf;
    private File file;

    private static ScoreManager instance;

    public synchronized static ScoreManager getinstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }
    private ScoreManager() {
        arrScore = new ArrayList<>();
    }

    public void getScore(){
        arrScore.clear();
        File file = new File(ScoreManager.class.getResource("/res/files/score.txt").getPath());
        if(!file.exists()){
            JOptionPane.showMessageDialog(null,"Error File");
            return;
        }
        try {
            raf=new RandomAccessFile(file,"rw");
            String line;
            String str;
            String[] part;
            while ((line =raf.readLine()) != null){
                str = new String(line.getBytes("ISO-8859-1"),"UTF-8");
                part = str.split("-");
                arrScore.add(new Score(part[0],Integer.parseInt(part[1])));
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeScore(){
        File file = new File(ScoreManager.class.getResource("/res/files/score.txt").getPath());
        if(file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            raf=new RandomAccessFile(file,"rw");
            String str="";
            System.out.println(arrScore.size());
            for (int i = 0; i < arrScore.size(); i++) {
                str+=arrScore.get(i).getName()+"-"+arrScore.get(i).getNum()+"\n";
            }
            raf.writeUTF(str);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkScore(int score){
        for (int i = 0; i < arrScore.size(); i++) {
            if(arrScore.get(i).getNum()<score)
                return true;
        }
        return false;
    }
    public void insertHighScore(String str,int score){
        arrScore.add(new Score(str,score));
        Collections.sort(arrScore, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o2.getNum()-o1.getNum();
            }
        });
        arrScore.remove(arrScore.size()-1);
    }

    public ArrayList<Score> getArrScore() {
        return arrScore;
    }
}
