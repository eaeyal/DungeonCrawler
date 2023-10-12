package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Leaderboard;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.RoomMapTile;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class InitialGameScreen extends AppCompatActivity {
    private Player player;
    private Timer scoreTimer;
    private TextView scoreText;

    private int screenWidth;
    private int screenHeight;

    private InitialGameScreenViewModel viewModel;

    private RoomMapTile roomMapTile;

    private ArrayList<ArrayList<Integer>> wallFloorStyles = new ArrayList<>();
    private int currentStyle = 0;

    protected void rebuildUi() {
        TextView playerName = findViewById(R.id.playerNameTextView);
        TextView playerHealth = findViewById(R.id.playerHealthTextView);

        playerName.setText(String.format("Player: %s", player.getName()));
        playerHealth.setText(String.format("Health: %d", player.getHealthPoints()));

        scoreText = findViewById(R.id.scoreTextView);
        scoreText.setText("Score: " + viewModel.getScore());

        playerName.setTranslationZ(1f);
        playerHealth.setTranslationZ(1f);
        scoreText.setTranslationZ(1f);

        RelativeLayout layout = findViewById(R.id.gameLayout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        roomMapTile.drawTileLayout(layout, screenWidth / 2, screenHeight / 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        viewModel = new InitialGameScreenViewModel();

        int floorTileImage = R.drawable.wooden_plank;
        int wallTileImage = R.drawable.wood;
        ArrayList<Integer> woodenLayout = new ArrayList<>(2);
        woodenLayout.add(floorTileImage);
        woodenLayout.add(wallTileImage);
        wallFloorStyles.add(woodenLayout);

        int floorStoneTileImage = R.drawable.stone_brick;
        int wallStoneTileImage = R.drawable.smooth_stone;
        ArrayList<Integer> stoneLayout = new ArrayList<>(2);
        stoneLayout.add(floorStoneTileImage);
        stoneLayout.add(wallStoneTileImage);
        wallFloorStyles.add(stoneLayout);

        int floorSandstoneTileImage = R.drawable.sandstone;
        int wallSandstoneTileImage = R.drawable.better_sandstone;
        ArrayList<Integer> sandstoneLayout = new ArrayList<>(2);
        sandstoneLayout.add(floorSandstoneTileImage);
        sandstoneLayout.add(wallSandstoneTileImage);
        wallFloorStyles.add(sandstoneLayout);

        roomMapTile = new RoomMapTile();
        roomMapTile.configTileFloorSpriteId(floorTileImage);
        roomMapTile.configTileWallSpriteId(wallTileImage);
        roomMapTile.configInvokeContext(this);
        roomMapTile.initPrimitiveTileLayout();

        ImageView playerSprite = findViewById(R.id.playerSprite);

        player = Player.getInstance();

        player.setCoordinates(150, 150);

        Button changeMapLayout = findViewById(R.id.btnChangeMap);
        changeMapLayout.setOnClickListener(v -> {
            // generate random number between 5 - 12
            int width = (int) (Math.random() * 7) + 5;
            int height = (int) (Math.random() * 7) + 5;

            currentStyle = (currentStyle + 1) % wallFloorStyles.size();
            ArrayList<Integer> nextStyle = wallFloorStyles.get(currentStyle);

            roomMapTile.configTileFloorSpriteId(nextStyle.get(0));
            roomMapTile.configTileWallSpriteId(nextStyle.get(1));

            roomMapTile.updateTileDimensionsAndRecomputeLayout(width, height,
                    (RelativeLayout) findViewById(R.id.gameLayout));

            roomMapTile.initPrimitiveTileLayout();
            roomMapTile.drawTileLayout((RelativeLayout) findViewById(R.id.gameLayout),
                    screenWidth / 2, screenHeight / 2);
        });

        Button endGameButton = findViewById(R.id.btnToEndGame);
        endGameButton.setOnClickListener(v -> {
            player.getScore();
            Leaderboard.getInstance().addScore(player.getName(), player.getScore(),
                    Calendar.getInstance().getTime().toString());
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

    @Override
    protected void onStart() {
        super.onStart();

        RelativeLayout layout = findViewById(R.id.gameLayout);
        roomMapTile.drawTileLayout(layout, screenWidth / 2, screenHeight / 2);
    }
}