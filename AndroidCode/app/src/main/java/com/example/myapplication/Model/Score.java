package com.example.myapplication.Model;

public class Score implements Comparable<Score> {
    private String player;
    private int score;
    private String time;
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

    public String getTime() {
        return time;
    }


    @Override
    public int compareTo(Score currentScore) {
        if (this.score > currentScore.score) {
            return -1;
        } else if (this.score < currentScore.score) {
            return 1;
        } else {
            return 0;
        }
    }
}
