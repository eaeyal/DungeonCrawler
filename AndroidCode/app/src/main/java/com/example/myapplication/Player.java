package com.example.myapplication;

public class Player {
    String name;
    int difficulty; // 1 for easy, 2 for medium, 3 for hard
    int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    String image; // link
    public Player(String name, int difficulty, String image) {
        this.name = name;
        this.difficulty = difficulty;
        this.healthPoints = 75 - (difficulty - 1) * 10;
        this.image = image;

    }
}
