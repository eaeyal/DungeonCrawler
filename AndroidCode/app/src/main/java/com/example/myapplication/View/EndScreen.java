package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.CustomBaseAdapter;
import com.example.myapplication.GameContext;
import com.example.myapplication.Leaderboard;
import com.example.myapplication.R;
import com.example.myapplication.Model.Score;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        listView = (ListView) findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),
                leaderboardEntries);
        listView.setAdapter(customBaseAdapter);



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


