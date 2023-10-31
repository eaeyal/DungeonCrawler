package com.example.myapplication.ViewModel;

import android.view.KeyEvent;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.EnemyController;
import com.example.myapplication.Model.SkeletonController;

public class InitialGameScreenViewModel {
    private Player player;
    private Runnable updatedCallback;

    private EnemyController skeleton;

    public InitialGameScreenViewModel() {
        this.player = Player.getInstance();
        this.player.setScore(100);
    }



    public Player getPlayer() {
        return player;
    }

    public void updateScore() {
        if (player.getScore() > 0) {
            player.setScore(player.getScore() - 1);
        }

        onUpdated();
    }

    public int getScore() {
        return player.getScore();
    }

    public void onUpdated() {
        if (updatedCallback != null) {
            updatedCallback.run();
        }
    }

    public void movePlayer(int keyCode) {
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
    }

    public void onUpdatedCallback(Runnable r) {
        updatedCallback = r;
    }

    // Enemy Controller Methods
    public void createSkeleton() {
        skeleton = new SkeletonController();
        skeleton.render();
    }
    public int getEnemyX(EnemyController enemyC) {
       return enemyC.enemy.getX();
    }
    public int getEnemyY(EnemyController enemyC) {
        return enemyC.enemy.getY();
    }

    // SetEnemy's shouldn't be used. If want to move enemy, can put logic into the Enemy class
    // movement() method. Is here for testing.
    public void setEnemyX(int x, EnemyController enemyC) {
        enemyC.enemy.setX(x);
    }

    public void setEnemyY(int y, EnemyController enemyC) {
        enemyC.enemy.setY(y);
    }
    public EnemyController getSkeleton() {
        return skeleton;
    }

    public void moveEnemy(EnemyController enemyC) {
        enemyC.movement();
    }

}
