package com.example.myapplication.Model;

import com.example.myapplication.GameContext;

public class Score {
    String player;
    int score;
    String time;
     public Score(String player, int score, String time) {
        player = GameContext.getInstance().getPlayerName();
        score = GameContext.getInstance().getScore();
        time = GameContext.getInstance().getTimeFormatted();
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public String getTime() { return time;}


}
