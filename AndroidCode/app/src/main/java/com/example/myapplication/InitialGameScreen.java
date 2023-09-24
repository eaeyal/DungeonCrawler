package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InitialGameScreen extends AppCompatActivity {
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);


        ImageView playerSprite = findViewById(R.id.playerSprite);

        player = new Player(
                GameContext.getInstance().getPlayerName(),
                GameContext.getInstance().getDifficulty(),
                GameContext.getInstance().getPlayerSprite(),
                playerSprite);

        player.setCoordinates(150, 150);


        TextView playerName = findViewById(R.id.playerNameTextView);
        TextView playerHealth = findViewById(R.id.playerHealthTextView);

        playerName.setText(String.format("Player: %s", player.getName()));
        playerHealth.setText(String.format("Health: %d", player.getHealthPoints()));

        Button endGameButton = findViewById(R.id.btnToEndGame);
        endGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(InitialGameScreen.this, EndScreen.class);
            startActivity(intent);
        });
    }
}