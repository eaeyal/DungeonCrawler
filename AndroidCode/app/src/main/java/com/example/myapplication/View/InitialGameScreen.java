package com.example.myapplication.View;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.GameContext;
import com.example.myapplication.Leaderboard;
import com.example.myapplication.Model.EnemyController;
import com.example.myapplication.Model.ExtraHealthPoints;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Physics.CollisionInfo;
import com.example.myapplication.Physics.RoomManager;
import com.example.myapplication.Physics.RoomMapTile;
import com.example.myapplication.Physics.TileType;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;
import com.example.myapplication.Model.Score;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class InitialGameScreen extends AppCompatActivity {
    private Player player = Player.getInstance();
    private Timer scoreTimer1;
    private Timer scoreTimer2;
    private TextView scoreText;
    private int screenWidth;
    private int screenHeight;
    private InitialGameScreenViewModel viewModel;
    private RoomManager roomManager;
    private ImageView playerSprite;

    private ImageView swordSprite;

    private ImageView slashSprite;

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

    protected Map<ImageView, EnemyController> enemies = new Hashtable<>(2);
    protected Timer enemyMovementTimer = null;

    protected ImageView instantiateImageViewForEnemy(int spriteId) {
        // weird stuff happens if a new instance of image view isn't recreated
        // and there is a significant change in replacement asset size
        RelativeLayout layout = findViewById(R.id.gameLayout);
        ImageView imageView = new ImageView(this);

        imageView.setImageResource(spriteId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(156);
        imageView.setMaxHeight(156);
        imageView.setTranslationZ(1f);
        layout.addView(imageView);
        return imageView;
    }

    protected ImageView instantiateImageViewForPowerUp(int spriteId) {
        RelativeLayout layout = findViewById(R.id.gameLayout);
        ImageView imageView = new ImageView(this);

        imageView.setImageResource(spriteId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(156);
        imageView.setMaxHeight(156);
        imageView.setTranslationZ(1f);
        layout.addView(imageView);
        return imageView;
    }

    protected ImageView instantiateImageViewForSword(int spriteId) {
        RelativeLayout layout = findViewById(R.id.gameLayout);
        ImageView imageView = new ImageView(this);

        imageView.setImageResource(spriteId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(80);
        imageView.setMaxHeight(80);
        imageView.setTranslationZ(1f);
        layout.addView(imageView);
        return imageView;
    }

    protected ImageView instantiateImageViewForSlash(int spriteId) {
        RelativeLayout layout = findViewById(R.id.gameLayout);
        ImageView imageView = new ImageView(this);

        imageView.setImageResource(spriteId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(80);
        imageView.setMaxHeight(80);
        imageView.setTranslationZ(1f);
        imageView.setVisibility(View.INVISIBLE);
        layout.addView(imageView);
        return imageView;
    }

    protected void instantiateEnemies() {
        // we already have enemies instantiated,
        // destroy them first (derender them)
        if (!enemies.isEmpty()) {
            enemies.forEach((imageView, enemyController) -> {
                imageView.setImageDrawable(null);
                // remove image view from canvas
                RelativeLayout layout = findViewById(R.id.gameLayout);
                layout.removeView(imageView);
            });
            enemies.clear();
        }


        //extraHealthPointsImageView.setVisibility(View.INVISIBLE); //TODO Richard

        //enemyFreezeImageView.setVisibility(View.INVISIBLE);


        // instantiate enemies based on the rooms we are currently in
        // (Can use factory method for this)
        switch (roomManager.getCurrentRoomIndex()) {
        case 0:
            //creating slime
            ImageView slime = instantiateImageViewForEnemy(R.drawable.thumbnail_slime);
            viewModel.createSlime();
            enemies.put(slime, viewModel.getSlime());

            //creating wizard
            ImageView wizard = instantiateImageViewForEnemy(R.drawable.thumbnail_wizard);
            viewModel.createWizard();
            enemies.put(wizard, viewModel.getWizard());

            //extra health power up
            viewModel.setExtraHealthPointsXPosition(600); //setting position X
            viewModel.setExtraHealthPointsYPosition(500); //setting position Y
            ImageView extraHealthPointsImageView = instantiateImageViewForPowerUp(R.drawable.powerup);
            extraHealthPointsImageView.setX(viewModel.getExtraHealthPointsX());
            extraHealthPointsImageView.setY(viewModel.getExtraHealthPointsY());

            break;
        case 1:
            //creating olaf enemy
            ImageView olaf = instantiateImageViewForEnemy(R.drawable.thumbnail_olaf);
            viewModel.createOlaf();
            enemies.put(olaf, viewModel.getOlaf());

            //creating skeleton enemy
            ImageView skeleton = instantiateImageViewForEnemy(R.drawable.thumbnail_skeleton);
            viewModel.createSkeleton();
            enemies.put(skeleton, viewModel.getSkeleton());

            /*
            //super speed power up
            viewModel.setSuperSpeedXPosition(500);
            viewModel.setSuperSpeedYPosition(1500);
            ImageView superSpeedImageView = instantiateImageViewForPowerUp(R.drawable.superspeed);
            superSpeedImageView.setX(viewModel.getSuperSpeedXPosition());
            superSpeedImageView.setY(viewModel.getSuperSpeedYPosition());

             */

            /*
            //enemy freeze power up
            viewModel.setEnemyFreezePositionX(822); //setting position X
            viewModel.setEnemyFreezePositionY(1505); //setting position Y
            ImageView enemyFreezeImageView = instantiateImageViewForPowerUp(R.drawable.snowflake);
            enemyFreezeImageView.setX(viewModel.getEnemyFreezePositionX());
            enemyFreezeImageView.setY(viewModel.getEnemyFreezePositionY());

            //TODO fix
            //player jump over enemy
            viewModel.setPlayerJumpEnemyPositionX(822);
            viewModel.setPlayerJumpEnemyPositionY(1500);
            ImageView playerJumpEnemyImageView = instantiateImageViewForPowerUp(R.drawable.snowflake);
            playerJumpEnemyImageView.setX(viewModel.getPlayerJumpEnemyPositionX());
            playerJumpEnemyImageView.setY(viewModel.getPlayerJumpEnemyPositionY());


             */

            //enemyFreezeImageView.setVisibility(View.VISIBLE);//?


            break;
        case 2:

            ImageView undead = instantiateImageViewForEnemy(R.drawable.undead);
            viewModel.createUndead();
            enemies.put(undead, viewModel.getUndead());

            ImageView boss = instantiateImageViewForEnemy(R.drawable.boss);
            viewModel.createBoss();
            enemies.put(boss, viewModel.getBoss());

            //extraHealthPointsImageView.setVisibility(View.INVISIBLE);

            /*
            //player jump over enemy
            viewModel.setPlayerJumpEnemyPositionX(1500);
            viewModel.setPlayerJumpEnemyPositionY(500);
            playerJumpEnemyImageView = instantiateImageViewForPowerUp(R.drawable.playerjumpenemy);
            playerJumpEnemyImageView.setX(viewModel.getPlayerJumpEnemyPositionX());
            playerJumpEnemyImageView.setY(viewModel.getPlayerJumpEnemyPositionY());


             */
            //extra health points
            extraHealthPointsImageView = instantiateImageViewForPowerUp(R.drawable.powerup);
            viewModel.setExtraHealthPointsXPosition(900); //setting position X
            viewModel.setExtraHealthPointsYPosition(823); //setting position Y
            extraHealthPointsImageView.setX(viewModel.getExtraHealthPointsX());
            extraHealthPointsImageView.setY(viewModel.getExtraHealthPointsY());

            break;
        default:
            throw new RuntimeException("Invalid room index");
        }

        // set the enemy sprites to be above the map
        enemies.forEach((imageView, enemyController) -> {
            viewModel.moveEnemy(enemyController);
            enemyController.movement();
            imageView.setX(viewModel.getEnemyX(enemyController));
            imageView.setY(viewModel.getEnemyY(enemyController));

        });
    }

    protected void moveEnemy() {
        enemies.forEach((imageView, enemyController) -> {
            viewModel.moveEnemy(enemyController);
            Log.i("MVMT", "Moving enemy" + enemyController.getClass() + " to: "
                    + viewModel.getEnemyX(enemyController)
                    + ", " + viewModel.getEnemyY(enemyController));
            imageView.setX(viewModel.getEnemyX(enemyController));
            imageView.setY(viewModel.getEnemyY(enemyController));
        });
    }

    //specifications for drawing powerup
    protected ImageView instantiateImageViewForPowerup(int spriteId) {
        RelativeLayout layout = findViewById(R.id.gameLayout);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(spriteId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(156);
        imageView.setMaxHeight(156);
        imageView.setX(500);
        imageView.setY(500);
        imageView.setTranslationZ(1f);
        layout.addView(imageView);
        return imageView;
    }

    //destroys powerup (must check for collision first then call powerup logic,
    //not sure where to do that)
    /*
    protected void destroyPowerup() {
        RelativeLayout layout = findViewById(R.id.gameLayout);
        if(powerupType == "red")
            layout.removeView(
        
        );
        if(powerupType == "blue")
            layout.removeView(bluePower);
        if(powerupType == "green")
            layout.removeView(greenPower);
    }

    //randomly picks powerup to draw
    protected void instantiatePowerups() {
        int rand = (int) Math.floor(Math.random() * 4);

        if(rand == 0) {
            redPower = instantiateImageViewForPowerup(R.drawable.red_pot);
            powerupType = "red";
        }
        else if (rand == 1) {
            bluePower = instantiateImageViewForPowerup(R.drawable.blue_pot);
            powerupType = "blue";
        }
        else {
            greenPower = instantiateImageViewForPowerup(R.drawable.green_pot);
            powerupType = "green";
        }

    }
    /*



    /**
     * @noinspection checkstyle:OperatorWrap
     */
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
                                R.drawable.wooden_plank, R.drawable.wood,
                                R.drawable.iron_door,
                                screenWidth / 2, screenHeight / 2)
                        .build(10, 15, this));

        roomManager.addRoom(
                RoomMapTile.fromTileStyle(
                                R.drawable.stone_brick, R.drawable.smooth_stone,
                                R.drawable.iron_door,
                                screenWidth / 2, screenHeight / 2)
                        .build(10, 15, this));

        roomManager.addRoom(
                RoomMapTile.fromTileStyle(
                                R.drawable.sandstone, R.drawable.better_sandstone,
                                R.drawable.oak_door,
                                screenWidth / 2, screenHeight / 2)
                        .build(10, 15, this));

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

        instantiateEnemies();

        scoreTimer1 = new Timer();
        scoreTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> viewModel.updateScore());
                checkCollision();
                moveEnemy();
                checkCollisionWithSlash();
                slashSprite.setVisibility(View.INVISIBLE);
                slashSprite.setX(0);
                slashSprite.setY(0);
            }
        }, 0, 1000); // Check every .5 seconds

        viewModel.onUpdatedCallback(this::rebuildUi);

        // set our player Z index to be above the map
        playerSprite.setTranslationZ(1f);
        // Bind our player movement callbacks

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
                player.updateCoordinatesWithoutNotification(screenWidth / 2, screenHeight / 2);
                player.setCoordinates(screenWidth / 2, screenHeight / 2);
                instantiateEnemies();
                rebuildUi();
            }

            collidingTiles.forEach((collisionInfo) -> {
                Log.i("COLLISION", "Player coordinates: " + player.getX() + ", " + player.getY());
                Log.i("COLLISION", "Colliding with tile: "
                        + collisionInfo.getTile().getType());
                player.resolveCollision(collisionInfo);
                collisionInfo.getTile().resolveCollision(collisionInfo);
            });
        });
        // initialize the player coordinate to the center of the screen
        player.setCoordinates(screenWidth / 2, screenHeight / 2);

        swordSprite = instantiateImageViewForSword(R.drawable.sword);
        viewModel.updateSwordPos();
        swordSprite.setX(viewModel.getSword().getX());
        swordSprite.setY(viewModel.getSword().getY());
        slashSprite = instantiateImageViewForSlash(R.drawable.slash);

    }

    public boolean isCollisionWithEnemy(ImageView player, ImageView enemy) {
        Rect rectPlayer = new Rect();
        player.getHitRect(rectPlayer);

        Rect rectEnemy = new Rect();
        enemy.getHitRect(rectEnemy);

        return Rect.intersects(rectPlayer, rectEnemy);
    }

    public void checkCollision() {
        if (player.getHealthPoints() >= 0) {
            this.enemies.forEach((imageView, enemyController) -> {
                if (isCollisionWithEnemy(playerSprite, imageView)) {
                    int diff = GameContext.getInstance().getDifficulty();
                    player.setHealthPoints(player.getHealthPoints() -
                            enemyController.getEnemy().getAttackDamage() * diff);
                }
                if (player.getHealthPoints() <= 0) {
                    Intent intent = new Intent(InitialGameScreen.this, GameOverScreen.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void checkCollisionWithSlash() {

        this.enemies.forEach((imageView, enemyController) -> {
            if (isCollisionWithEnemy(slashSprite, imageView)) {
                imageView.setVisibility(View.INVISIBLE);
                enemyController.getEnemy().setAttackDamage(0);
                //When added score bugs game out removed will fix later
            }
        });

    }

    public void swordSlash() {
        slashSprite.setVisibility(View.VISIBLE);
        slashSprite.setX(viewModel.getSword().getX() + 85);
        slashSprite.setY(viewModel.getSword().getY());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // move player
        viewModel.movePlayer(keyCode);
        viewModel.powerUps();
        viewModel.updateSwordPos();
        swordSprite.setX(viewModel.getSword().getX());
        swordSprite.setY(viewModel.getSword().getY());
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            swordSlash();
        }
        if (player.getHealthPoints() <= 0) {
            Intent intent = new Intent(InitialGameScreen.this, GameOverScreen.class);
            startActivity(intent);
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
