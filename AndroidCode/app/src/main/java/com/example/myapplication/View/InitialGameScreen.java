package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;


public class InitialGameScreen extends AppCompatActivity {
    private Player player;
    private int score = 100;
    private Timer scoreTimer;
    private TextView scoreText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        scoreText = findViewById(R.id.scoreTextView);
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
            GameContext.getInstance().setScore(score);
            // Adding score here and play name as parameters does nothing since called addScore will get it from GameContext
            GameContext.getInstance().getLeaderboard().addScore(player.getName(), score, "Nov 5, 12:30");
            Intent intent = new Intent(InitialGameScreen.this, EndScreen.class);
            startActivity(intent);
        });

        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateScore();
                    }
                });
            }
        }, 0, 1000); // Check every .5 seconds
    }
    private void updateScore() {
        if (score > 0) {
            score -= 5;
            scoreText.setText("Score: " + score);
        }
    }
}