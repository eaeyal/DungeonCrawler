package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class InitialGameScreen extends AppCompatActivity {
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        player = new Player(
                GameContext.getInstance().getPlayerName(),
                GameContext.getInstance().getDifficulty(),
                ((Integer)(GameContext.getInstance().getPlayerSprite())).toString());

        TextView playerName = findViewById(R.id.playerNameTextView);
        TextView playerHealth = findViewById(R.id.playerHealthTextView);

        playerName.setText(String.format("Player: %s", player.name));
        playerHealth.setText(String.format("Health: %d", player.healthPoints));

        Button endGameButton = findViewById(R.id.btnToEndGame);
        endGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(InitialGameScreen.this, EndScreen.class);
            startActivity(intent);
        });
    }
}