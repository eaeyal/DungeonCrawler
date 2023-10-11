package com.example.myapplication.Model;

import com.example.myapplication.GameContext;

public class Score {
    String player;
    int score;
    String time;
     public Score(String player, int score, String time) {
        this.player = player;
        this.score = score;
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public String getTime() { return time;}


}
