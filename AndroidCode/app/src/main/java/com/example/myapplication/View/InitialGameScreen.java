package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Leaderboard;
import com.example.myapplication.Model.Player;
import com.example.myapplication.R;
import com.example.myapplication.RoomMapTile;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;
import com.example.myapplication.ViewModel.Subscriber;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class InitialGameScreen extends AppCompatActivity {
    private Player player = Player.getInstance();
    private Timer scoreTimer;
    private TextView scoreText;

    private int screenWidth;
    private int screenHeight;

    private InitialGameScreenViewModel viewModel;

    private RoomMapTile roomMapTile;

    private ImageView playerSprite;

    private ArrayList<ArrayList<Integer>> wallFloorStyles = new ArrayList<>();
    private int currentStyle = 0;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

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

        playerSprite = findViewById(R.id.playerSprite); //Player Sprite image set //TODO


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


        // set our player Z index to be above the map
        playerSprite.setTranslationZ(1f);
        // Bind our player movement callbacks
        Player.getInstance().subscribe((player) -> {
            playerSprite.setX(player.getX());
            playerSprite.setY(player.getY());
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // move player
        Player player = Player.getInstance();
        if (keyCode == KeyEvent.KEYCODE_A) {
            player.setXCoordinate(player.getXCoordinate() - 10);
        }
        if (keyCode == KeyEvent.KEYCODE_D) {
            player.setXCoordinate(player.getXCoordinate() + 10);
        }
        if (keyCode == KeyEvent.KEYCODE_W) {
            player.setYCoordinate(player.getYCoordinate() - 10);
        }
        if (keyCode == KeyEvent.KEYCODE_S) {
            player.setYCoordinate(player.getYCoordinate() + 10);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RelativeLayout layout = findViewById(R.id.gameLayout);
        roomMapTile.drawTileLayout(layout, screenWidth / 2, screenHeight / 2);
    }
}