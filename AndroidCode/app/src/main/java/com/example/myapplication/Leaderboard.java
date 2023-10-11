package com.example.myapplication;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.Activity;

import com.example.myapplication.Model.Score;

/**
 * Stores scores from the leaderboard in the a list, called scoreEntry
 */
public class Leaderboard extends Activity {

    private List<Score> scoreEntry = new ArrayList<>(); //list to store scores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
    }

    public void addScore(String player, int score, String time) {
        String playerName = GameContext.getInstance().getPlayerName();
        scoreEntry.add(new Score(playerName, score, GameContext.getInstance().getTimeFormatted()));
    } //adding scores to list

    public List<Score> getScores() {
        Collections.sort(scoreEntry, Collections.reverseOrder()); //sort in descending order
        return scoreEntry;
    }
}

