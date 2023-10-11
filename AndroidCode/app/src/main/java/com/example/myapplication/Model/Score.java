package com.example.myapplication.Model;

import com.example.myapplication.GameContext;

public class Score implements Comparable<Score> {
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


    @Override
    public int compareTo(Score o) {
        if (this.score > o.score) {
            return -1;
        } else if (this.score < o.score) {
            return 1;
        } else {
            return 0;
        }
    }
}
