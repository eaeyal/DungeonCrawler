package com.example.myapplication;

public class GameContxt {
    private Player player;
    private int difficulty;

    // we can store scores here etc.

    private GameContxt() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private static GameContxt _instance = null;
    public static GameContxt getInstance() {
        if (_instance == null) {
            _instance = new GameContxt();
        }

        return _instance;
    }
}
