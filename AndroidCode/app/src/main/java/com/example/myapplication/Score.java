package com.example.myapplication;

public class Score {
    String player;
    int score;
    String time;
     Score(String player, int score, String time) {
        player = GameContext.getInstance().getPlayerName();
        score = GameContext.getInstance().getScore();
        time = GameContext.getInstance().timeFormatted;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public String getTime() { return time;}


}
