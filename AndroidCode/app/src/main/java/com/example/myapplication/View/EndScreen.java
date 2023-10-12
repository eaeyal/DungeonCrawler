package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.ScoreAdapter;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Leaderboard;
import com.example.myapplication.Model.Score;
import com.example.myapplication.R;

import java.util.ArrayList;

public class EndScreen extends AppCompatActivity { //comment for pragya: like main activity
    private ListView listView;
    private ArrayList<Score> leaderboardEntries = Leaderboard.getInstance().getScoresSorted();
    private ScoreAdapter customListViewScoreAdapter;

    //getting the list of scores
    private Leaderboard leaderboard = Leaderboard.getInstance();
    private TextView scoreDisplayView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        listView = (ListView) findViewById(R.id.scoresListView);
        customListViewScoreAdapter = new ScoreAdapter(this, leaderboardEntries);
        listView.setAdapter(customListViewScoreAdapter);

        scoreDisplayView = findViewById(R.id.scoreDisplay);
        scoreDisplayView.setText("Score: " + Player.getInstance().getScore());

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}


