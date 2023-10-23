package com.example.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Leaderboard;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Physics.CollisionInfo;
import com.example.myapplication.Physics.RoomManager;
import com.example.myapplication.Physics.RoomMapTile;
import com.example.myapplication.Physics.TileType;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;
import com.example.myapplication.ViewModel.Subscriber;

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

    private RoomManager roomManager;

    private ImageView playerSprite;

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

        roomManager.drawRoom(layout);
    }

    protected void gotoEndScreen() {
        Leaderboard.getInstance().addScore(player.getName(), player.getScore(),
                Calendar.getInstance().getTime().toString());
        Intent intent = new Intent(InitialGameScreen.this, EndScreen.class);
        startActivity(intent);
    }

    /** @noinspection checkstyle:OperatorWrap*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game_screen);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        viewModel = new InitialGameScreenViewModel();
        roomManager = new RoomManager();

        roomManager.addRoom(
                RoomMapTile.fromTileStyle(
                        R.drawable.wooden_plank, R.drawable.wood, R.drawable.iron_door,
                        screenWidth / 2, screenHeight / 2)
                        .build(5, 5, this));

        roomManager.addRoom(
                RoomMapTile.fromTileStyle(
                        R.drawable.stone_brick, R.drawable.smooth_stone, R.drawable.iron_door,
                        screenWidth / 2, screenHeight / 2)
                        .build(5, 5, this));

        roomManager.addRoom(
                RoomMapTile.fromTileStyle(
                        R.drawable.sandstone, R.drawable.better_sandstone, R.drawable.oak_door,
                        screenWidth / 2, screenHeight / 2)
                        .build(5, 5, this));

        playerSprite = findViewById(R.id.playerSprite); //Player Sprite image set
        playerSprite.setMaxWidth(56);
        playerSprite.setMaxHeight(56);
        playerSprite.setMinimumHeight(56);
        playerSprite.setMinimumWidth(56);
        if (player.getImage() == 1) {
            playerSprite.setImageResource(R.drawable.player1);
        } else if (player.getImage() == 2) {
            playerSprite.setImageResource(R.drawable.player2);
        } else {
            playerSprite.setImageResource(R.drawable.player3);
        }

        Button endGameButton = findViewById(R.id.btnToEndGame);
        endGameButton.setOnClickListener(v -> {
            player.setX(500000);
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

        viewModel.onUpdatedCallback(this::rebuildUi);

        // set our player Z index to be above the map
        playerSprite.setTranslationZ(1f);
        // Bind our player movement callbacks

        Player.getInstance().subscribe(new Subscriber() {
            @Override
            public void update(Player player) {

            }
        });

        Player.getInstance().subscribe((player) -> {

        });

        Player.getInstance().subscribe((player) -> {
            playerSprite.setX(player.getX());
            playerSprite.setY(player.getY());

            List<CollisionInfo> collidingTiles = roomManager.getCurrentRoom()
                    .getCollidingTiles(player.getX(), player.getY(), 56, 56);
            // if we are only colliding with door tiles or floor tiles, change room
            boolean shouldChangeRoom = true;
            boolean hasExitTile = false;
            for (CollisionInfo collisionInfo : collidingTiles) {
                if (collisionInfo.getTile().getType() != TileType.Exit
                        && collisionInfo.getTile().getType() != TileType.Floor) {
                    shouldChangeRoom = false;
                    break;
                }

                if (collisionInfo.getTile().getType() == TileType.Exit) {
                    hasExitTile = true;
                }
            }

            if (shouldChangeRoom && hasExitTile) {
                int currentRoomIndex = roomManager.getCurrentRoomIndex();

                // end game if we are in the last room, that is,
                // currentRoomIndex == roomManager.getTotalRoomCount() - 1
                if (currentRoomIndex == roomManager.getTotalRoomCount() - 1) {
                    // rest the room index to 0
                    roomManager.changeRoom(0);

                    gotoEndScreen();
                    player.setWinner();
                    player.addRoom("wooden plank", "stone brick", "sandstone");
                    return;
                }

                int nextRoomIndex = (currentRoomIndex + 1) % roomManager.getTotalRoomCount();
                roomManager.changeRoom(nextRoomIndex);

                // rest the player's coordinates to the center of the screen
                player.setCoordinatesNoNotify(screenWidth / 2, screenHeight / 2);
                player.setCoordinates(screenWidth / 2, screenHeight / 2);
                rebuildUi();
            }

            collidingTiles.forEach((collisionInfo) -> {
                Log.i("COLLISION", "Colliding with tile: "
                        + collisionInfo.getTile().getType());
                player.resolveCollision(collisionInfo);
                collisionInfo.getTile().resolveCollision(collisionInfo);
            });
        });
        // initialize the player coordinate to the center of the screen
        player.setCoordinates(screenWidth / 2, screenHeight / 2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // move player
        Player player = Player.getInstance();
        // prospect player coordinates

        int playerX = player.getXCoordinate();
        int playerY = player.getYCoordinate();

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
        roomManager.drawRoom(layout);
    }
}