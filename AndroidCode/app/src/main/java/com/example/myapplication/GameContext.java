package com.example.myapplication;

public class GameContext {
    public static float DifficultyToHealthCoefficient(int difficulty) {
        switch (difficulty) {
            case 2:
                return 0.65f;
            case 3:
                return 0.50f;
            default:
                return 0.75f;
        }
    }

    private Player player;
    private int difficulty;

    // we can store scores here etc.

    private GameContext() {

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

    private static GameContext _instance = null;
    public static GameContext getInstance() {
        if (_instance == null) {
            _instance = new GameContext();
        }

        return _instance;
    }
}
