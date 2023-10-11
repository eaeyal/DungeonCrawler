package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class EndScreen extends AppCompatActivity { //comment for pragya: like main activity
    ListView listView;
   List<Score> leaderboardEntries = GameContext.getInstance().getLeaderboard().getScores();
    //getting the list of scores
    Leaderboard leaderboard = GameContext.getInstance().getLeaderboard();

    public void Leaderboard() {
        leaderboard.addScore("Pragya",100, "Nov 5, 12:30");
        leaderboard.getScores();
    }

    private TextView scoreDisplayView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),
                leaderboardEntries);
        listView.setAdapter(customBaseAdapter);

        scoreDisplayView = findViewById(R.id.scoreDisplay);
        scoreDisplayView.setText("Score: " + GameContext.getInstance().getScore());

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }




    /*
    ID, name, icon, list order, limits
     */

}


