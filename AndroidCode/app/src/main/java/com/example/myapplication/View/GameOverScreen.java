package com.example.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Leaderboard;

import java.util.Calendar;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;


public class GameOverScreen extends AppCompatActivity {
    private Player player = Player.getInstance();
    private ImageView lossByEnemy;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loss_by_enemy);

        Button backButton = findViewById(R.id.button3);
        backButton.setText("Return");
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameOverScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button leaderBoardButton = findViewById(R.id.button4);
        leaderBoardButton.setText("LeaderBoard");
        leaderBoardButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(GameOverScreen.this, EndScreen.class);
                startActivity(intent);
            }
        });
    }
}
