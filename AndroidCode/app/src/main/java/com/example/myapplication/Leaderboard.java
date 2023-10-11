package com.example.myapplication;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.app.Activity;

import com.example.myapplication.Model.Score;

/**
 * Stores scores from the leaderboard in the a list, called scoreEntry
 */
public class Leaderboard extends Activity {

    private String[] scoreEntry = new String[5]; //list to store scores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
    }

    public void addScore(String player, int score, String time, int position) {
        String playerName = GameContext.getInstance().getPlayerName();
        scoreEntry[position] = playerName + " " + score + " " +GameContext.getInstance().getTimeFormatted();
        position++;
    } //adding scores to list

    public String[] getScores() {
        Arrays.sort(scoreEntry, Collections.reverseOrder()); //sort in descending order
        return scoreEntry;
    }
}

