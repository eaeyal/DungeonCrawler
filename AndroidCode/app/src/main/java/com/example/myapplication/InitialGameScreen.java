package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InitialGameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        TextView playerName = findViewById(R.id.playerNameTextView);
        TextView playerHealth = findViewById(R.id.playerHealthTextView);

        playerName.setText(GameContext.getInstance().getPlayer().name);
        playerHealth.setText(String.valueOf(GameContext.getInstance().getPlayer().healthPoints));
    }
}