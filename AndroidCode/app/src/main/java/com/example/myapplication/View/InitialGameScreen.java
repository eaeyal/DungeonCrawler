package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;

import java.util.Timer;
import java.util.TimerTask;


public class InitialGameScreen extends AppCompatActivity {
    private Player player;
    private Timer scoreTimer;
    private TextView scoreText;

    private InitialGameScreenViewModel viewModel;

    protected void rebuildUi() {
        TextView playerName = findViewById(R.id.playerNameTextView);
        TextView playerHealth = findViewById(R.id.playerHealthTextView);

        playerName.setText(String.format("Player: %s", player.getName()));
        playerHealth.setText(String.format("Health: %d", player.getHealthPoints()));

        scoreText = findViewById(R.id.scoreTextView);
        scoreText.setText("Score: " + viewModel.getScore());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        viewModel = new InitialGameScreenViewModel();

        ImageView playerSprite = findViewById(R.id.playerSprite);

        player = Player.getInstance();

        player.setCoordinates(150, 150);

        Button endGameButton = findViewById(R.id.btnToEndGame);
        endGameButton.setOnClickListener(v -> {
            player.getScore();
            // Adding score here and play name as parameters does nothing since called addScore will get it from GameContext

            //GameContext.getInstance().getLeaderboard().addScore(player.getName(), score, "Nov 5, 12:30");
            Intent intent = new Intent(InitialGameScreen.this, EndScreen.class);
            startActivity(intent);
        });

        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> viewModel.updateScore());
            }
        }, 0, 1000); // Check every .5 seconds

        viewModel.onUpdatedCallback(() -> {
            rebuildUi();
        });
    }


}