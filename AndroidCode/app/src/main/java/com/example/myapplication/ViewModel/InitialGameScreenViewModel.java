package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.BasePowerUp;
import com.example.myapplication.Model.ExtraHealthPoints;


import android.view.KeyEvent;

import com.example.myapplication.Model.BossController;
import com.example.myapplication.Model.EnemyController;
import com.example.myapplication.Model.OlafController;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.PowerUpInterface;
import com.example.myapplication.Model.PowerUpInterface;
import com.example.myapplication.Model.PowerUps;
import com.example.myapplication.Model.PowerupsEnemyFreeze;
import com.example.myapplication.Model.PowerupsPlayerJumpEnemy;
import com.example.myapplication.Model.SkeletonController;
import com.example.myapplication.Model.SlimeController;
import com.example.myapplication.Model.SuperSpeed;
import com.example.myapplication.Model.UndeadController;
import com.example.myapplication.Model.WizardController;
import com.example.myapplication.Model.Sword;

public class InitialGameScreenViewModel {
    private Player player;
    private Runnable updatedCallback;
    private EnemyController skeleton;
    private EnemyController boss;
    private EnemyController slime;
    private EnemyController undead;
    private EnemyController olaf;
    private EnemyController wizard;
    private Player playerInstance = Player.getInstance();


    //PowerUpInterface playerWithSuperSpeed = new SuperSpeed(playerWithExtraHealth);
    //private PowerupsPlayerJumpEnemy playerJumpEnemy = new PowerupsPlayerJumpEnemy();

    private Sword sword = new Sword();

    public Sword getSword() {
        return this.sword;
    }

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
        // prospect player coordinates
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
        powerUps();
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
        enemyC.movement(); //TODO freeze enemies maybe put a timer
        /*if (enemyFreeze.getPowerUps() == false) {
            enemyC.movement();
        } else {

         */

        //}
    }

    //setting initialize position
    public void setExtraHealthPointsXPosition(int X) {
        playerWithExtraHealth.setXCoordinate(X);
    }

    public int getExtraHealthPointsX() {
        return playerWithExtraHealth.getXCoordinate();
    }

    public void setExtraHealthPointsYPosition(int Y) {
        playerWithExtraHealth.setYCoordinate(Y);
    }

    public int getExtraHealthPointsY() {
        return playerWithExtraHealth.getYCoordinate();
    }

    /*
    public void setEnemyFreezePositionX(int X) {
        enemyFreeze.setX(X);
    }
    public int getEnemyFreezePositionX() {
        return enemyFreeze.getX();
    }
    public void setEnemyFreezePositionY(int Y) {
        enemyFreeze.setY(Y);
    }
    public int getEnemyFreezePositionY() {
        return enemyFreeze.getY();
    }

     */
/*
    public void setSuperSpeedXPosition(int X) {
        playerWithSuperSpeed.setXCoordinate(X);
    }
    public int getSuperSpeedXPosition() {
        return playerWithSuperSpeed.getXCoordinate();
    }
    public void setSuperSpeedYPosition(int Y) {
        playerWithSuperSpeed.setYCoordinate(Y);
    }
    public int getSuperSpeedYPosition() {
        return playerWithSuperSpeed.getYCoordinate();
    }

    public void setPlayerJumpEnemyPositionX(int X) { playerJumpEnemy.setX(X); }
    public int getPlayerJumpEnemyPositionX() {
        return playerJumpEnemy.getX();
    }
    public void setPlayerJumpEnemyPositionY(int Y) {
        playerJumpEnemy.setY(Y);
    }
    public int getPlayerJumpEnemyPositionY() {
        return playerJumpEnemy.getY();
    }
    */

    public void updateSwordPos() {
        sword.setX(player.getX() + 60);
        sword.setY(player.getY() + 20);
    }

    public void setSuperSpeedXPosition(int X) {
        playerWithSuperSpeed.setXCoordinate(X);
    }
    public int getSuperSpeedXPosition() {
        return playerWithSuperSpeed.getXCoordinate();
    }
    public void setSuperSpeedYPosition(int Y) {
        playerWithSuperSpeed.setYCoordinate(Y);
    }
    public int getSuperSpeedYPosition() {
        return playerWithSuperSpeed.getYCoordinate();
    }
    /*
    public void setPlayerJumpEnemyPositionX(int X) { playerWithEnemyJump.setXCoordinate(X); }
    public int getPlayerJumpEnemyPositionX() {
        return playerWithEnemyJump.getXCoordinate();
    }
    public void setPlayerJumpEnemyPositionY(int Y) {
        playerWithEnemyJump.setYCoordinate(Y);
    }
    public int getPlayerJumpEnemyPositionY() {
        return playerWithEnemyJump.getYCoordinate();
    }

     */

    public void setEnemyFreezePositionX(int X) { playerWithEnemyFreeze.setXCoordinate(X); }
    public int getEnemyFreezePositionX() {
        return playerWithEnemyFreeze.getXCoordinate();
    }
    public void setEnemyFreezePositionY(int Y) {
        playerWithEnemyFreeze.setYCoordinate(Y);
    }
    public int getEnemyFreezePositionY() {
        return playerWithEnemyFreeze.getYCoordinate();
    }

    PowerUpInterface playerWithEnemyFreeze = new PowerupsEnemyFreeze(new BasePowerUp());
    PowerUpInterface playerWithExtraHealth = new ExtraHealthPoints(new BasePowerUp());
    PowerUpInterface playerWithSuperSpeed = new SuperSpeed(new BasePowerUp());

    //PowerUpInterface playerWithEnemyJump = new PowerupsPlayerJumpEnemy(new BasePowerUp());
    public void powerUps() {
        if (Player.getInstance().getX() >= getExtraHealthPointsX() &&
                Player.getInstance().getX() <= getExtraHealthPointsX() + 156 &&
                Player.getInstance().getY() >= getExtraHealthPointsY() &&
                Player.getInstance().getY() <= getExtraHealthPointsY() + 156) {
            if (playerWithExtraHealth.getPowerUps() == false) {
                playerWithExtraHealth.powerUp();
                playerWithExtraHealth.setPowerUps(true);
            }

        }
        if (Player.getInstance().getX() >= getSuperSpeedXPosition() &&
                Player.getInstance().getX() <= getSuperSpeedXPosition() + 156 &&
                Player.getInstance().getY() >= getSuperSpeedYPosition() &&
                Player.getInstance().getY() <= getSuperSpeedYPosition() + 156) {
            if (playerWithSuperSpeed.getPowerUps() == false) {
                playerWithSuperSpeed.powerUp();
                playerWithSuperSpeed.setPowerUps(true);
            }

        }

        /*if (Player.getInstance().getX() >= getPlayerJumpEnemyPositionX() &&
                Player.getInstance().getX() <= getPlayerJumpEnemyPositionX() + 156 &&
                Player.getInstance().getY() >= getPlayerJumpEnemyPositionY() &&
                Player.getInstance().getY() <= getPlayerJumpEnemyPositionY() + 156) {
            if (playerWithEnemyJump.getPowerUps() == false) {
                getBoss().getEnemy().setAttackDamage(0);
                getUndead().getEnemy().setAttackDamage(0);
                playerWithEnemyJump.setPowerUps(true);
            }
        }

         */
        if (Player.getInstance().getX() >= getEnemyFreezePositionX() &&
                Player.getInstance().getX() <= getEnemyFreezePositionX() + 156 &&
                Player.getInstance().getY() >= getEnemyFreezePositionY() &&
                Player.getInstance().getY() <= getEnemyFreezePositionY() + 156) {
            if (playerWithEnemyFreeze.getPowerUps() == false) {
                getBoss().getEnemy().setSpeed(0);
                getUndead().getEnemy().setSpeed(0);
                playerWithEnemyFreeze.setPowerUps(true);
            }
        }

    }
}
