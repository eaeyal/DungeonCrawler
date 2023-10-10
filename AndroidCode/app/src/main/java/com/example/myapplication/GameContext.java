package com.example.myapplication;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GameContext {
    private String playerName;
    private int difficulty;
    private int playerSprite;
    private Leaderboard leaderboard = new Leaderboard();

    Date time = Calendar.getInstance().getTime();
    String timeFormatted = DateFormat.getDateInstance().format(time);

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

    public String getDateAndTime() {
        return timeFormatted;
    }

    private static GameContext instance = null;
    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard = new Leaderboard();
    }




}
