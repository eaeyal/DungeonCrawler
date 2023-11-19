package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.ExtraHealthPoints;


import android.view.KeyEvent;

import com.example.myapplication.Model.BossController;
import com.example.myapplication.Model.EnemyController;
import com.example.myapplication.Model.OlafController;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.SkeletonController;
import com.example.myapplication.Model.SlimeController;
import com.example.myapplication.Model.SuperSpeed;
import com.example.myapplication.Model.UndeadController;
import com.example.myapplication.Model.WizardController;

public class InitialGameScreenViewModel {
    private Player player;
    private Runnable updatedCallback;
    private EnemyController skeleton;
    private EnemyController boss;
    private EnemyController slime;
    private EnemyController undead;
    private EnemyController olaf;
    private EnemyController wizard;

    private ExtraHealthPoints extraHealthPoints = new ExtraHealthPoints(0, 0);
    private SuperSpeed superSpeed = new SuperSpeed();
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
        player.setSpeed(5); //TODO put an if/else statement if player has powerup
        int speed = player.getSpeed();

        int playerX = player.getXCoordinate();
        int playerY = player.getYCoordinate();

        if (keyCode == KeyEvent.KEYCODE_A) {
            player.setXCoordinate(player.getXCoordinate() - speed);
        }
        if (keyCode == KeyEvent.KEYCODE_D) {
            player.setXCoordinate(player.getXCoordinate() + speed);
        }
        if (keyCode == KeyEvent.KEYCODE_W) {
            player.setYCoordinate(player.getYCoordinate() - speed);
        }
        if (keyCode == KeyEvent.KEYCODE_S) {
            player.setYCoordinate(player.getYCoordinate() + speed);
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

    public void createBoss() {
        boss = new BossController();
        boss.render();
    }

    public void createSlime() {
        slime = new SlimeController();
        slime.render();
    }

    public void createUndead() {
        undead = new UndeadController();
        undead.render();
    }

    public void createOlaf() {
        olaf = new OlafController();
        olaf.render();
    }

    public void createWizard() {
        wizard = new WizardController();
        wizard.render();
    }

    public int getEnemyX(EnemyController enemyC) {
        return enemyC.getEnemy().getX();
    }
    public int getEnemyY(EnemyController enemyC) {
        return enemyC.getEnemy().getY();
    }

    // SetEnemy's shouldn't be used. If want to move enemy, can put logic into the Enemy class
    // movement() method. Is here for testing.
    public void setEnemyX(int x, EnemyController enemyC) {
        enemyC.getEnemy().setX(x);
    }

    public void setEnemyY(int y, EnemyController enemyC) {
        enemyC.getEnemy().setY(y);
    }
    public EnemyController getSkeleton() {
        return skeleton;
    }

    public EnemyController getBoss() {
        return boss;
    }

    public EnemyController getSlime() {
        return slime;
    }

    public EnemyController getUndead() {
        return undead;
    }

    public EnemyController getOlaf() {
        return olaf;
    }

    public EnemyController getWizard() {
        return wizard;
    }
    public void moveEnemy(EnemyController enemyC) {
        enemyC.movement();
    }

    //setting initialize position
    public void setExtraHealthPointsXPosition(int X) {
        extraHealthPoints.setX(X);
    }
    public int getExtraHealthPointsX() {
        return extraHealthPoints.getX();
    }

    public void setExtraHealthPointsYPosition(int Y) {
        extraHealthPoints.setY(Y);
    }
    public int getExtraHealthPointsY() {
        return extraHealthPoints.getY();
    }

    public void setSuperSpeedXPosition(int X) {
        superSpeed.setX(X);
    }
    public int getSuperSpeedXPosition() {
        return superSpeed.getX();
    }
    public void setSuperSpeedYPosition(int Y) {
        superSpeed.setY(Y);
    }
    public int getSuperSpeedYPosition() {
        return superSpeed.getY();
    }
    public void powerUps() {
        //TODO implement
    }


}
