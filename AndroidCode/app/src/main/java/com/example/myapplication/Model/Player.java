package com.example.myapplication.Model;

import android.widget.ImageView;

import com.example.myapplication.R;

public class Player {
    private String name;
    private int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    private int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3

    private int score;

    private int x = 0;
    private int y = 0;

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private Player() {
        this.name = null;
        this.healthPoints = -1;
        this.image = -1;
    }

    private Player(String name, int difficulty, int image) {
        this.name = name;
        this.healthPoints = 75 - (difficulty - 1) * 10;
        this.image = image;
        this.setCoordinates(x, y);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
