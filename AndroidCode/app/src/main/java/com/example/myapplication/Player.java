package com.example.myapplication;

public class Player {
    String name;
    int difficulty; // 1 for easy, 2 for medium, 3 for hard
    int healthPoints; // 75 for easy, 50 for medium, 25 for hard
    String image; // link
    public Player(String name, int difficulty, String image) {
        this.name = name;
        this.difficulty = difficulty;
        this.healthPoints = (4 - difficulty) * 25;
        this.image = image;

    }
}
