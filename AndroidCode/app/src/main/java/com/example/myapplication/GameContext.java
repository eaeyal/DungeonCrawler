package com.example.myapplication;

import kotlin.text.UStringsKt;

public class GameContext {
    private String playerName;
    private int difficulty;
    private int playerSprite;

    // we can store scores here etc.

    private GameContext() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(int playerSprite) {
        this.playerSprite = playerSprite;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private static GameContext _instance = null;
    public static GameContext getInstance() {
        if (_instance == null) {
            _instance = new GameContext();
        }

        return _instance;
    }
}
