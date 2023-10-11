package com.example.myapplication;
import com.example.myapplication.Model.Player;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GameContext {
    private int difficulty;

    GameContext() {

    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private static GameContext instance = null;
    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }
}
