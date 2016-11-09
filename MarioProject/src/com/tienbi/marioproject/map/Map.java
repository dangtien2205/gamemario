package com.tienbi.marioproject.map;

import com.tienbi.marioproject.gui.GUI;
import com.tienbi.marioproject.manager.GameManager;
import com.tienbi.marioproject.model.Item;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by TienBi on 23/07/2016.
 */
public class Map {
    // position
    private int x;
    private int y;

    // bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private ArrayList<Item> items;

    // drawing

    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public ArrayList<Item> getItems() {
        return items;
    }

    public Map(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GUI.HEIGHT / tileSize + 2;
        numColsToDraw = GUI.WIDTH / tileSize + 2;
        items = new ArrayList<>();
    }

    public void loadMap(String fileName) {
        try {
            items.clear();
            File file = new File(GameManager.class.getResource("/res/maps/" + fileName).getPath());
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "file map bi loi");
                return;
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            numCols = Integer.parseInt(raf.readLine());
            numRows = Integer.parseInt(raf.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GUI.WIDTH - width;
            xmax = 0;
            ymin = GUI.HEIGHT - height;
            ymax = 0;

            for (int row = 0; row < numRows; row++) {
                String line = raf.readLine();
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = line.charAt(col) - '0';
                    if (map[row][col] == 0)
                        continue;
                    items.add(new Item(col * Item.SIZE, row * Item.SIZE, map[row][col]));
                }
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x);
        this.y += (y - this.y);
        fixBounds();
        colOffset = (int) -this.x / tileSize;
    }

    private void fixBounds() {
        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < numRowsToDraw; row++) {
            if (row >= numRows) break;
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= numCols) break;

                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    if (item.getTile(col, row)) {
                        item.setMapPosition(x, y);
                        item.draw(g2);
                    }
                }
            }
        }
    }
}
